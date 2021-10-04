package Commands;


import java.util.Scanner;

public class CommandMaxById implements Command{
    private static final long serialVersionUID = -3649928705995173479L;

    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
