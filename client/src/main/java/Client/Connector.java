package Client;


import tools.ClientLogger;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;


public class Connector{
    InetSocketAddress serverAddress;
    DatagramSocket client;

    ByteArrayOutputStream b1 = new ByteArrayOutputStream(2048);
    ObjectOutputStream outputStream;
    ObjectInput input;

    byte[] buffer = new byte[2048];

    public Connector(int PORT) {
        try {
            serverAddress = new InetSocketAddress("localhost", PORT);
            client = new DatagramSocket();
            outputStream = new ObjectOutputStream(b1);
        } catch (IOException e) {
            ClientLogger.logger.error("Ошибка в конструкторе Коннектора", e);
        }
    }


    public void send(Object data) {
        try {
            b1 = new ByteArrayOutputStream(2048);
            outputStream = new ObjectOutputStream(b1);
            outputStream.writeObject(data);

            DatagramPacket packet = new DatagramPacket(b1.toByteArray(), b1.size(), serverAddress);
            client.send(packet);
        } catch (IOException e) {
            ClientLogger.logger.error("Ошибка при отправке данных", e);
        }
    }

    public String receive(int timeout) {
            try {
                client.setSoTimeout(timeout);
                buffer = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                client.receive(packet);
                input = new ObjectInputStream(new ByteArrayInputStream(buffer));
                return (String) input.readObject();
            } catch (SocketTimeoutException e) {
                ClientLogger.logger.error("Время ожидание ответа истекло");
                return null;
            } catch (IOException | ClassNotFoundException e) {
                ClientLogger.logger.error("Ошибка при принятии данных", e);
                return "Ошибка";
            }
    }
}


