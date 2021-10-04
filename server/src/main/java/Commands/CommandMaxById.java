package Commands;


import Commands.Command;
import Main.CollectionManager;

import java.util.Scanner;

public class CommandMaxById implements Command{
    private static final long serialVersionUID = -3649928705995173479L;

    CollectionManager manager;

    public CommandMaxById(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandMaxById() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.max_by_id();
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
