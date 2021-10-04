package Commands;

import Main.CollectionManager;

import java.util.Scanner;

public class CommandReady  implements Command {

    CollectionManager manager;

    public CommandReady() {
    }

    public CommandReady(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.ready();
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
