package Commands;

import content.StudyGroup;
import tools.Checker;

import java.io.Serializable;
import java.util.Scanner;

public class CommandReplaceIfGreater implements Command, Serializable {
    private static final long serialVersionUID = -5531871344777781959L;

    String key;
    StudyGroup studyGroup;
    @Override
    public boolean validate(String argument, Scanner reader) {
        if (argument == null || argument.equals("")) {
            System.out.println("Ошибка! Ключ должен быть не пустой строкой. Повторите ввод команды.");
            return false;
        }
        CommandInsert a = new CommandInsert();
        a.validate(argument,reader);
        this.studyGroup = a.studyGroup;
        this.key = argument;
        return true;
    }
}
