package Commands;

import Commands.Command;
import Main.CollectionManager;

import java.util.Scanner;

public class CommandRemoveGreater implements Command{
    private static final long serialVersionUID = -3236822596483462976L;
    CollectionManager manager;

    public CommandRemoveGreater(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandRemoveGreater() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.remove_greater();
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
