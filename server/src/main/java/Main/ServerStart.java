package Main;

import Commands.CommandSave;
import content.Location;
import content.Person;
import content.StudyGroup;
import tools.ServerLogger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class ServerStart {
    static int PORT;
    static HashMap<SocketAddress,Connector> users = new HashMap<>();
    static String filepath;
    static CollectionManager manager;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteBuffer buffer;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        if (args.length > 0) filepath = args[0];
        else filepath = "collection.xml";
        fillCollection(filepath);

        try {
            System.out.print("Введите порт для подключения: ");
            PORT = Integer.parseInt(new Scanner(System.in).next());
            if (PORT < 0 || PORT >65535) throw new Exception();
        }catch (Exception e){
            System.out.println("Ошибка чтения порта, используется значение по умолчанию - 1025.");
            PORT = 1025;
        } //Чтение порта

        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress("localhost", PORT));
            channel.configureBlocking(false);
            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ);
            buffer = ByteBuffer.allocate(2048);
            ServerLogger.logger.info("Запуск сервера на порту {}", PORT);
            System.out.println("Ожидание подключения...");

            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it;
                for (it = keys.iterator(); it.hasNext(); ) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (in.ready()) {
                        String command = in.readLine().trim();
                        if (command.equals("save")) {
                            new CommandSave(manager).execute();
                        }
                    }
                    if (key.isValid()) {
                        if (key.isReadable()) {
                            SocketAddress remoteAddress = channel.receive(buffer);
                            if (!users.containsKey(remoteAddress)) {
                                System.out.println("Соединение с пользователем " + remoteAddress.toString() + " установлено.");
                                ServerLogger.logger.info("Соединение с пользователем " + remoteAddress.toString() + ":" + " установлено.");
                                Connector connector = new Connector(remoteAddress, channel, buffer);
                                users.put(remoteAddress, connector);
                            }
                            users.get(remoteAddress).receive();
                            buffer.clear();
                        } // read
                    }
                }
            }

        } catch (BindException e) {
            System.out.println("Порт занят.");
        }

    }

    /**
     * Заполнить коллекцию из файла
     * @param filePath путь до файла
     */
    private static void fillCollection(String filePath){
        JAXBContext context;
        Unmarshaller unmarshaller;
        BufferedInputStream stream;
        try {
            context = JAXBContext.newInstance(StudyGroup.class, CollectionManager.class, Person.class, Location.class);
            unmarshaller = context.createUnmarshaller();
            stream = new BufferedInputStream(new FileInputStream(filePath));
            manager = (CollectionManager) unmarshaller.unmarshal(stream);
            Iterator<Map.Entry<String, StudyGroup>> iterator = manager.getStudyGroups().entrySet().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getValue().isEmpty()) {
                    System.out.println("Ошибка! Одна из групп не была добавлена в коллекцию, т.к. одно или несколько полей не были указаны, либо выходят за допустимый диапазон.");
                    iterator.remove();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Ошибка! Невозможно считать коллекцию из файла, т.к. одно или несколько полей указаны в некорректном формате (например, на месте числа - строка).");
            ServerLogger.logger.error("Ошибка! Невозможно считать коллекцию из файла, т.к. одно или несколько полей указаны в некорректном формате (например, на месте числа - строка).");

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл с входными данными не найден, проверьте путь и права доступа к файлу.");
            ServerLogger.logger.error("Ошибка! Файл с входными данными не найден, проверьте путь и права доступа к файлу.");

        } catch (JAXBException e) {
            System.out.println("Ошибка при десериализации документа. Проверьте правильность разметки.");
            ServerLogger.logger.error("Ошибка при десериализации документа. Проверьте правильность разметки.");

        }
    }
}


