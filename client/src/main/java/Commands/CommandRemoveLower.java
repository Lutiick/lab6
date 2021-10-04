package Commands;

import java.util.Scanner;

public class CommandRemoveLower implements Command{
    private static final long serialVersionUID = -3059982635993501584L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
