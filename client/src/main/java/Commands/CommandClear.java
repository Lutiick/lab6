package Commands;

import java.io.Serializable;
import java.util.Scanner;

public class CommandClear implements Command, Serializable {
    private static final long serialVersionUID = 935077734379123140L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}

