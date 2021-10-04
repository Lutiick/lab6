package Commands;

import java.util.Scanner;

public class CommandSave implements Command{
    private static final long serialVersionUID = -4040580807252619688L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}

