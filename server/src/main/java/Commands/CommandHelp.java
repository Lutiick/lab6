package Commands;

import Main.CollectionManager;

import java.io.Serializable;
import java.util.Scanner;

public class CommandHelp implements Command, Serializable {

    CollectionManager manager;

    public CommandHelp(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandHelp() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.help();
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
