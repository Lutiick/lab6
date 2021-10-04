package Main;

import Commands.CommandInsert;
import content.*;
import tools.Checker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс, обеспечивающий доступ к коллекции
 */

@XmlType(name = "root")
@XmlRootElement
public class CollectionManager {
    @XmlElementWrapper(name = "collection")
    private final static Hashtable<String, StudyGroup> studyGroups = new Hashtable<>();
    private final static ZonedDateTime initDate = ZonedDateTime.now();
    private final String[] temp = new String[11];
    private Handler handler;
    private static final Set<String> pathList = new HashSet<>();
    Connector connector;

    public CollectionManager() {
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public static ZonedDateTime getInitDate() {
        return initDate;
    }

    /**
     * Получить информацию о командах
     */
    public void help() {
        String manual = "help вывести справку по доступным командам\n" +
                "info вывести информацию о коллекции\n" +
                "show вывести все элементы коллекции\n" +
                "insert key {element} добавить новый элемент с заданным ключом\n" +
                "update id {element} обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key удалить элемент из коллекции по его ключу\n" +
                "clear очистить коллекцию\n" +
                //"save сохранить коллекцию в файл\n" +
                "execute_script file_name считать и исполнить скрипт из указанного файла.\n" +
                "exit завершить программу\n" +
                "remove_greater {element} удалить из коллекции максимальный элемент\n" +
                "remove_lower {element} удалить из коллекции минимальный элемент\n" +
                "replace_if_greater {element} заменить значение по ключу, если новое значение больше старого\n" +
                "max_by_id вывести любой объект из коллекции, значение поля id которого является максимальным\n" +
                "filter_by_group_admin groupAdmin вывести элементы, значение поля groupAdmin которых равно заданному\n" +
                "print_field_descending_group_admin вывести значения поля groupAdmin всех элементов в порядке убывания";
        connector.send(manual);
    }

    /**
     * Получить информацию о коллекции
     */
    public void info() {
        String info = "Тип - " + studyGroups.getClass().getName() +
                "\nДата инициализации - " + getInitDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")) +
                "\nКоличество элементов - " + studyGroups.size();
        connector.send(info);
    }

    /**
     * Команда-маркер подключения к серверу
     */
    public void ready(){
        connector.send("Подключение установлено.");
    }

    /**
     * Показать элементы коллекции
     */
    public void show() {
        if (studyGroups.size() == 0) {
            connector.send("Коллекция пуста");
            return;
        }
        String result = studyGroups.entrySet().stream()
                .map(
                        (Map.Entry<String, StudyGroup> entry) ->
                                "Ключ " + entry.getKey() + "\n" + entry.getValue().NiceToString()

                ).reduce((res, val) -> res + "\n" + val)
                .get();
        connector.send(result);
    }

    public void exit(){
        handler.isExit = true;
    }

    /**
     * Добавить элемент коллекции
     */
    public void insert(){
        connector.send("===================================\nЭлемент успешно добавлен/обновлён.\"");
    }

    /**
     * Обновить существующий элемент коллекции по его id
     * @param id : id (не индекс) нужного элемента
     */
    public void update(String id, StudyGroup argument){
        System.out.println("hello3");
        System.out.println(id);
        try {
            Map.Entry<String, StudyGroup> entry = studyGroups.entrySet()
                    .stream()
                    .filter((Map.Entry<String, StudyGroup> e) ->
                            e.getValue().getId().equals(Integer.parseInt(id)))
                    .findFirst().get();
            studyGroups.put(entry.getKey(), argument);
        } catch (NoSuchElementException e){
            connector.send("Элемент с указанным ключом не найден.");
            return;
        }

        connector.send("===================================\nЭлемент успешно обновлён.");
    }

    /**
     * Удалить элемент коллекции по его id
     * @param key ключ нужного элемента
     */
    public void remove_key(String key) {
        if (key != null && !key.equals("")) {
            if (studyGroups.containsKey(key)) {
                studyGroups.remove(key);
                connector.send("Элемент с ключом " + key + " успешно удален");
            }
            else
                connector.send("Элемента с key = '" + key + "' не найдено.");
        } else {
            connector.send("Ошибка! 'key' должен быть не пустой строкой. Повторите ввод команды.");
        }
    }

    /**
     * Очистить коллекцию
     */
    public void clear() {
        studyGroups.clear();
        connector.send("Коллекция успешно очищена.");
    }

    /**
     * Сохранить коллекцию в файл
     * @param manager : объект для доступа к коллекции
     * @throws IOException если файл не найден или защищен
     * @throws JAXBException если не удалось сериализовать коллекцию
     */
    public void save(CollectionManager manager){
        try {
            FileWriter writer = new FileWriter("collection.xml");
            JAXBContext context = JAXBContext.newInstance(StudyGroup.class, CollectionManager.class, Person.class, Location.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(manager, writer);
            System.out.println("Коллекция успешно сохранена.");
        }catch (FileNotFoundException e) {
            System.out.println("Ошибка. Файл для сохранения не найден, проверьте путь и доступ к файлу.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения.");
        } catch (MarshalException e) {
            System.out.println("Ошибка сериализации коллекции в XML.");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вывести максимальный элемент коллекции по id
     */
    public void max_by_id() {
        if (studyGroups.size() > 0) {
            Map.Entry<String, StudyGroup> maxEntry = studyGroups.entrySet()
                    .stream()
                    .max(Comparator.comparing((Map.Entry<String, StudyGroup> e) -> e.getValue().getId()))
                    .get();

            connector.send(
                    "Описание элемента с максимальным значением поля 'id':\n" +
                            "Ключ " + maxEntry.getKey() + "\n" +
                            maxEntry.getValue().NiceToString()
            );
        } else connector.send("Ошибка. Коллекция пуста.");
    }

    /**
     * Вывести админов групп
     */
    public void print_admins() {
        if (studyGroups.size() > 0) {
            String result = studyGroups.values()
                    .stream()
                    .sorted(Comparator.comparing(StudyGroup::getId).reversed())
                    .map(StudyGroup::getGroupAdmin)
                    .map(Person::NiceToString)
                    .reduce((res, val) -> res + "\n=====================" + val)
                    .get();
            connector.send(result);
        }  else connector.send("Ошибка. Коллекция пуста.");
    }

    /**
     * Вывести группы с админами равными name
     */
    public void filter_by_group_admin(String name) {
        String result = "";
        if (studyGroups.size() > 0) {
            if (Checker.isNotString(name)) {
                connector.send("Неккоректный аргумент");
                return;
            }
            result = studyGroups.values()
                    .stream()
                    .filter(studyGroup -> studyGroup.getGroupAdmin().getName().equals(name))
                    .map(
                            studyGroup ->
                                    "Описание группы с админом по имени: " + name + "\n" +
                                            studyGroup.NiceToString()
                    )
                    .reduce((res, value) -> res + "\n" + value).get();
            connector.send(result);
        } else connector.send("Ошибка. Коллекция пуста.");
    }




    /**
     * Удалить елемент с макс числом студентов
     */
    public void remove_greater() {
        if (studyGroups.size() > 0) {
            String key = studyGroups.entrySet()
                    .stream()
                    .max(Comparator.comparing((Map.Entry<String, StudyGroup> e) -> e.getValue().getStudentsCount()))
                    .get()
                    .getKey();
            studyGroups.remove(key);
            connector.send("Елемент с ключем " + key + "удален");
        } else connector.send("Ошибка. Коллекция пуста.");
    }

    /**
     * Удалить елемент с мин числом студентов
     */
    public void remove_lower() {
        String result = "";
        if (studyGroups.size() > 0) {
            String key = studyGroups.entrySet()
                    .stream()
                    .min(Comparator.comparing((Map.Entry<String, StudyGroup> e) -> e.getValue().getStudentsCount()))
                    .get()
                    .getKey();
            studyGroups.remove(key);
            result += "Елемент с ключем " + key + "удален";
        } else result += "Ошибка. Коллекция пуста.";
        connector.send(result);
    }

    public void replace_if_greater(String key, StudyGroup studyGroup) {
        String result = "";
        if (key != null && !key.equals("")) {
            if (!studyGroups.containsKey(key)) {
                if (studyGroups.get(key).compareTo(studyGroup) < 0) {
                    studyGroups.put(key, studyGroup);
                    result += "Елемент с ключем " + key + " обновлен";
                } else {
                    result += "Ошибка! Новое значение меньше старого";
                }
            } else {
                result += "Ошибка! Ключа нет в коллекции";
            }
        } else {
            result += "Ошибка! Неправильный аргумент";
        }
        connector.send(result);
    }


    /**
     * Выполнить скрипт из заданного файла
     * @param path Путь до файла
     * @throws FileNotFoundException если файл недоступен/не найден
     */
    public void execute_script(String path) throws FileNotFoundException {
        InputStream stream;
        String result = "";
        try {
            if (path.startsWith("/dev")) {
                connector.send("Файл для извлечения скрипта не найден. Проверьте путь и права доступа к файлу.");
                return;
            }
        }catch (StringIndexOutOfBoundsException ignored){

        }
        try{
            stream = new BufferedInputStream(new FileInputStream(path));
        }catch (FileNotFoundException e){
            connector.send("Файл для извлечения скрипта не найден. Проверьте путь и права доступа к файлу.");
            return;
        }
        if (stream.equals(System.in)) {
            pathList.clear();
        } else {
            if (pathList.contains(path)) {
                connector.send("#############################################\nОшибка! Один или несколько скриптов зациклены.\n#############################################");
                return;
            }
        }
        //Проверка на зацикленность
        pathList.add(path);
        connector.send("start");
        connector.send("====  Начало выполнения скрипта по адресу " + path + "  ====\n");
        handler.interactiveMod(stream);
        connector.send("end");
        connector.send("====  Скрипт " + path + " успешно выполнен  ====\n");
        pathList.remove(path);
    }


    /**
     * Получить объект коллекции
     * @return коллекцию
     */
    public Hashtable<String, StudyGroup> getStudyGroups() {
        return studyGroups;
    }

}