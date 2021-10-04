package Commands;

import Main.CollectionManager;

import java.util.Scanner;

public class CommandInfo implements Command {
    static final long serialVersionUID = 2935568159828483139L;
    CollectionManager manager;

    public CommandInfo() {
    }

    public CommandInfo(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.info();
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
