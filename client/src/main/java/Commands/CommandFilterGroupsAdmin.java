package Commands;

import tools.Checker;

import java.io.Serializable;
import java.util.Scanner;

public class CommandFilterGroupsAdmin implements Command, Serializable {
    private static final long serialVersionUID = -4349945673456783479L;
    String argument;

    public CommandFilterGroupsAdmin() {
    }

    public boolean validate(String argument, Scanner reader) {
        if (Checker.isNotString(argument)) {
            System.out.println("Неккоректный аргумент");
            return false;
        }
        this.argument = argument;
        return true;
    }
}
