package Commands;


import java.util.Scanner;

public class CommandReady implements Command {
    private static final long serialVersionUID = 2070506952673629004L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
