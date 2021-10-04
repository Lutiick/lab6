package Commands;

import Main.CollectionManager;
import tools.Checker;

import java.util.Scanner;

public class CommandRemoveKey implements Command{
    private static final long serialVersionUID = 4540012222739611587L;
    CollectionManager manager;
    String argument;

    public CommandRemoveKey(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandRemoveKey() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.remove_key(argument);
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        if (Checker.isNotString(argument)) {
            System.out.println("Ошибка! 'key' должен быть не пустой строкой. Повторите ввод команды.");
            return false;
        }
        this.argument = argument;
        return true;
    }
}

