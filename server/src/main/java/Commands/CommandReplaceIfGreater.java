package Commands;

import Main.CollectionManager;
import content.StudyGroup;

import java.io.Serializable;
import java.util.Scanner;

public class CommandReplaceIfGreater implements Command, Serializable {
    private static final long serialVersionUID = -5531871344777781959L;
    CollectionManager manager;
    String key;
    StudyGroup studyGroup;

    public CommandReplaceIfGreater(CollectionManager manager) {
        this.manager = manager;
    }

    public CommandReplaceIfGreater() {
    }

    @Override
    public void setManager(CollectionManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {

        manager.replace_if_greater(key, studyGroup);
    }

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
