package Commands;

import Main.CollectionManager;

import java.io.Serializable;
import java.util.Scanner;

public class CommandRemoveLower implements Command, Serializable {
    private static final long serialVersionUID = -3059982635993501584L;
    CollectionManager manager;

    public CommandRemoveLower(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandRemoveLower() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.remove_lower();
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
