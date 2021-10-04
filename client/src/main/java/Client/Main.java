package Client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Commander commander;
        int PORT;
        try {
            System.out.print("Введите порт для подключения: ");
            PORT = Integer.parseInt(new Scanner(System.in).next());
            if (PORT < 0 || PORT >65535) throw new Exception();
        }catch (Exception e){
            System.out.println("Ошибка чтения порта, используется значение по умолчанию.");
            PORT = 1216;
        }

        commander = new Commander(PORT);
        if (commander.connect()) {
            commander.interactiveMod(System.in);
        }
    }

}