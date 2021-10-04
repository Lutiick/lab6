package Client;


import Commands.*;
import tools.ClientLogger;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, обеспечивающий обработку пользовательских команд
 */
public class Commander {

    Invoker invoker = new Invoker();
    Connector connector;
    boolean connected = false;
    int PORT;
    public Commander(int PORT){
        this.PORT = PORT;
        connector = new Connector(PORT);

        invoker.register("help", new CommandHelp());
        invoker.register("info", new CommandInfo());
        invoker.register("show", new CommandShow());
        invoker.register("insert", new CommandInsert());
        invoker.register("update", new CommandUpdate());
        invoker.register("remove_key", new CommandRemoveKey());
        invoker.register("clear", new CommandClear());
        invoker.register("execute_script", new CommandExecuteScript());
        invoker.register("exit", new CommandExit());
        invoker.register("remove_greater", new CommandRemoveGreater());
        invoker.register("remove_lower", new CommandRemoveLower());
        invoker.register("replace_if_greater", new CommandReplaceIfGreater());
        invoker.register("max_by_id", new CommandMaxById());
        invoker.register("filter_by_group_admin", new CommandFilterGroupsAdmin());
        invoker.register("print_field_descending_group_admin", new CommandPrintAdmins());
    }

    public Commander() {
    }

    public boolean connect() {
        String result;
        System.out.println("Ожидание ответа сервера(10 секунд)...");
        connector.send(new CommandReady());
        ClientLogger.logger.info("Попытка подключения к серверу с портом {}",PORT);
        result = connector.receive(10000);
        if (result == null) {
            ClientLogger.logger.error("Неудачная попытка соединения: сервер с портом {} не отвечает", PORT);
            System.out.println("Неудачная попытка соединения: сервер с портом " + PORT + " не отвечает");
            connected = false;
        } else {
            connected = true;
            ClientLogger.logger.info("Подключено к серверу с портом {}", PORT);
            System.out.println(result);
        }
        return connected;
    }


    /**
     * Включить интерактивный режим
     * @param stream : поток ввода
     */
    public void interactiveMod(InputStream stream) {
        if (!connected) {
            return;
        }
        boolean valid;
        String result;
        String fullUserCommand = "";
        if (stream.equals(System.in))
            System.out.println("***\tНачало работы. Для просмотра доступных команд напишите 'help'\t***");
        try (Scanner commandReader = new Scanner(stream)) {
            do{
                fullUserCommand = commandReader.nextLine();
                String[] command = fullUserCommand.trim().split(" ");
                if (invoker.contains(command[0])) {
                    if (command.length > 1)
                        valid = invoker.validate(command[0], command[1], commandReader);
                    else
                        valid = invoker.validate(command[0], "null", commandReader);

                    if (!valid) continue;
                    if (command[0].equals("execute_script")) {
                        connector.send(invoker.getCommandMap().get(command[0]));
                        result = connector.receive(5000);
                        if (result.equals("start")) {
                            while(true) {
                                result = connector.receive(5000);
                                if (result == null || result.equals("end")) {
                                    break;
                                } else {
                                    System.out.println(result);
                                }
                            }
                        }
                    } else {
                        connector.send(invoker.getCommandMap().get(command[0]));
                    }

                    result = connector.receive(5000);
                    if (result != null) {
                        System.out.println(result);
                    } else {
                        System.out.println("Ответа от сервера не было получено\nПопытка переподключения");
                        if(!connect())
                            break;
                    }

                } else
                    System.out.println("Неопознанная команда! Введите 'help' для просмотра доступных команд.");

            }while (!fullUserCommand.equals("exit") && commandReader.hasNext());
        }catch (NoSuchElementException e){ClientLogger.logger.error("Ошибка в интерактивном режиме", e);}
        System.out.println("***\tВыход из интерактивного режима\t***");
        ClientLogger.logger.info("Отключение от сервера");
    }
}
