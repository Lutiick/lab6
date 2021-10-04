package Commands;

import Main.CollectionManager;

import java.io.Serializable;
import java.util.Scanner;

public interface Command extends Serializable {
    void execute();
    boolean validate(String argument, Scanner reader);
    void setManager(CollectionManager manager);
}
