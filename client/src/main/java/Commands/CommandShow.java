package Commands;

import java.util.Scanner;

public class CommandShow implements Command {
    private static final long serialVersionUID = -6736089393672297269L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
