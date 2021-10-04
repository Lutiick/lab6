package Commands;

import Main.CollectionManager;

import java.io.Serializable;
import java.util.Scanner;

public class CommandClear implements Command, Serializable {

    CollectionManager manager;

    public CommandClear(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandClear() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.clear();
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}

