package Commands;

import java.io.Serializable;
import java.util.Scanner;

public interface Command extends Serializable {
    boolean validate(String argument, Scanner reader);
}
