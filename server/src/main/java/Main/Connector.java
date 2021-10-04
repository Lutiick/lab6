package Main;

import Commands.Command;
import tools.ServerLogger;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Connector{

    protected final SocketAddress userSocketAddress; //Сокет-адрес подключенного пользователя

    Command command; //Актуальная команда для выполнения Handler'ом
    Handler handler;
    ByteArrayOutputStream b1 = new ByteArrayOutputStream(2048); //Потоки для отправки ответов
    ObjectOutputStream outputStream;
    ObjectInputStream input;
    ByteBuffer buffer;
    DatagramChannel channel;

    public Connector(SocketAddress address, DatagramChannel channel, ByteBuffer buffer) {
        handler = new Handler(this);
        this.buffer = buffer;
        this.userSocketAddress = address;
        this.channel = channel;
    }

    /**
     * Принять данные от пользователя
     * @param data - данные (как правило, объект команды)
     */
    public void send(Object data){
        try {
            buffer.clear();
            b1 = new ByteArrayOutputStream(2048);
            outputStream = new ObjectOutputStream(b1);
            outputStream.writeObject(data);
            buffer.put(b1.toByteArray());
            buffer.flip();
            channel.send(buffer, userSocketAddress);
        }catch (IOException exception){
            ServerLogger.logger.error("",exception);
        }
    }

    /**
     * Отправить пользователю данные
     */
    public void receive(){
        try {
            buffer.flip();
            input = new ObjectInputStream(new ByteArrayInputStream(this.buffer.array()));
            command = (Command) input.readObject();
            System.out.println(command);
            handler.runCommand();
        } catch (IOException | ClassNotFoundException e) {
            ServerLogger.logger.error("Ошибка при получении данных",e);
        }

    }
}
