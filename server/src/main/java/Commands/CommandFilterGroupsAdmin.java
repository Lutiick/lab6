package Commands;

import Main.CollectionManager;
import tools.Checker;

import java.io.Serializable;
import java.util.Scanner;

public class CommandFilterGroupsAdmin implements Command, Serializable {
    private static final long serialVersionUID = -4349945673456783479L;
    CollectionManager manager;
    String argument;

    public CommandFilterGroupsAdmin(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandFilterGroupsAdmin() {
    }

    @Override
    public void execute() {
        manager.filter_by_group_admin(argument);
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        if (Checker.isNotString(argument)) {
            System.out.println("Неккоректный аргумент");
            return false;
        }
        this.argument = argument;
        return true;
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }
}
