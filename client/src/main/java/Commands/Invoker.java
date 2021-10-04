package Commands;

import java.util.HashMap;
import java.util.Scanner;

public class Invoker {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public boolean contains(String commandName){
        return commandMap.containsKey(commandName);
    }

    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    public boolean validate(String commandName, String argument, Scanner reader) {
        Command command = commandMap.get(commandName);
        return command.validate(argument, reader);
    }
}

