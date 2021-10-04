package Commands;

import java.io.Serializable;
import java.util.Scanner;

public class CommandExecuteScript implements Command, Serializable {
    private static final long serialVersionUID =  7699342124433530839L;
    String argument;

    @Override
    public boolean validate(String argument, Scanner reader) {
        this.argument = argument;
        return true;
    }
}
