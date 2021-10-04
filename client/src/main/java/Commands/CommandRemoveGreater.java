package Commands;

import java.util.Scanner;

public class CommandRemoveGreater implements Command{
    private static final long serialVersionUID = -3236822596483462976L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
