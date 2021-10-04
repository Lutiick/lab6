package Commands;

import Main.CollectionManager;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class CommandExecuteScript implements Command, Serializable {
    private static final long serialVersionUID =  7699342124433530839L;
    CollectionManager manager;
    String argument;

    public CommandExecuteScript(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandExecuteScript() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(){
        try {
            manager.execute_script(argument);
        }catch (FileNotFoundException e){
            System.out.println("Файл для извлечения скрипта не найден. Проверьте путь и права доступа к файлу.");
        }
    }

    @Override
    public boolean validate(String argument, Scanner reader) {
        this.argument = argument;
        return true;
    }
}
