package Commands;

import content.StudyGroup;
import tools.Checker;

import java.io.Serializable;
import java.util.Scanner;

public class CommandUpdate implements Command, Serializable {
    static final long serialVersionUID = 2215821813093036001L;

    StudyGroup studyGroup;
    String id;

    @Override
    public boolean validate(String argument, Scanner reader) {
        if (!Checker.isInteger(argument) || Integer.parseInt(argument) <= 0) {
            System.out.println("Ошибка! 'id' должен быть целым положительным числом. Повторите ввод команды.");
            return false;
        }
        CommandInsert a = new CommandInsert();
        a.validate(argument,reader);
        this.studyGroup = a.studyGroup;
        this.id = argument;
        return true;
    }
}

