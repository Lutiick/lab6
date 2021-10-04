package Commands;


import java.io.Serializable;
import java.util.Scanner;

public class CommandHelp implements Command, Serializable {
    private static final long serialVersionUID = -4848347544087315082L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
