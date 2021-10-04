package Main;

import Commands.*;
import tools.ServerLogger;

import java.io.*;
import java.util.Scanner;

public class Handler implements Serializable {
    Invoker invoker = new Invoker();
    Connector connector;
    CollectionManager manager = new CollectionManager();
    protected boolean isExit = false;

    BufferedInputStream stream;

    {
        invoker.register("help", new CommandHelp(manager));
        invoker.register("info", new CommandInfo(manager));
        invoker.register("show", new CommandShow(manager));
        invoker.register("insert", new CommandInsert(manager));
        invoker.register("update", new CommandUpdate(manager));
        invoker.register("remove_key", new CommandRemoveKey(manager));
        invoker.register("clear", new CommandClear(manager));
        invoker.register("save", new CommandSave(manager));
        invoker.register("execute_script", new CommandExecuteScript(manager));
        invoker.register("exit", new CommandExit(manager));
        invoker.register("remove_greater", new CommandRemoveGreater(manager));
        invoker.register("remove_lower", new CommandRemoveLower(manager));
        invoker.register("replace_if_greater", new CommandReplaceIfGreater(manager));
        invoker.register("max_by_id", new CommandMaxById(manager));
        invoker.register("filter_by_group_admin", new CommandFilterGroupsAdmin(manager));
        invoker.register("print_field_descending_group_admin", new CommandPrintAdmins(manager));

    }

    public Handler(Connector connector){
        this.connector = connector;
        manager.setConnector(connector);
        manager.setHandler(this);

    }

    public BufferedInputStream getStream() {
        return stream;
    }

    /**
     * Эмуляция интерактивного режима для выполнения скрипта
     * @param stream - поток ввода из скрипта
     */
    public void interactiveMod(InputStream stream) {
        String fullUserCommand = "";
        try (Scanner commandReader = new Scanner(stream)) {
            while (!fullUserCommand.equals("exit") && commandReader.hasNext()) {
                fullUserCommand = commandReader.nextLine();
                String[] command = fullUserCommand.trim().split(" ");
                if (invoker.contains(command[0])) {
                    try {
                        if (invoker.validate(command[0], command[1], commandReader)) {
                            invoker.execute(invoker.getCommandMap().get(command[0]));
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        if (invoker.validate(command[0], "null", commandReader)) {
                            invoker.execute(invoker.getCommandMap().get(command[0]));
                        }
                    }
                }

            }
        }
    }

    public void runCommand() {
        Command command = connector.command;
        command.setManager(manager);
        invoker.execute(command);
    }
}

