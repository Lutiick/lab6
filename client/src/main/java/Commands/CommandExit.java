package Commands;

import java.io.Serializable;
import java.util.Scanner;

public class CommandExit implements Command, Serializable {
    private static final long serialVersionUID = -3985681923210095903L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
