package Commands;

import tools.Checker;

import java.util.Scanner;

public class CommandRemoveKey implements Command{
    private static final long serialVersionUID = 4540012222739611587L;
    String argument;

    @Override
    public boolean validate(String argument, Scanner reader) {
        if (Checker.isNotString(argument)) {
            System.out.println("Ошибка! 'key' должен быть не пустой строкой. Повторите ввод команды.");
            return false;
        }
        this.argument = argument;
        return true;
    }
}

