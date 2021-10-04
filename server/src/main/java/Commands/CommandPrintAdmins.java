package Commands;


import Commands.Command;
import Main.CollectionManager;

import java.util.Scanner;

public class CommandPrintAdmins implements Command {
    CollectionManager manager;
    private static final long serialVersionUID = 2070506952673629004L;

    public CommandPrintAdmins(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandPrintAdmins() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.print_admins();
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
