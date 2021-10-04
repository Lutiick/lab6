package Commands;

import java.io.Serializable;
import java.util.Scanner;

public class CommandInfo implements Command, Serializable {
    static final long serialVersionUID = 2935568159828483139L;
    @Override
    public boolean validate(String argument, Scanner reader) {
        return true;
    }
}
