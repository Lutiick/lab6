package Commands;

import Commands.Command;
import content.*;
import tools.Checker;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static tools.Validator.makeValidationCycle;

public class CommandInsert implements Command, Serializable {
    static final long serialVersionUID = 8823381577385926588L;

    String argument;
    StudyGroup studyGroup;

    @Override
    public boolean validate(String argument, Scanner commandReader) {
        try {
            final String[] temp = new String[15];
            if (Checker.isNotString(argument)) {
                throw new IllegalArgumentException();
            }
            temp[0] = makeValidationCycle(commandReader, 0, "Введите имя группы: ",
                    "Ошибка! Строка не должна быть пустой",
                    (String s1) -> s1.equals(""));

            temp[1] = makeValidationCycle(commandReader, 1, "Введите координату X: ",
                    "Ошибка! Координата должна быть числом меньшим 610.",
                    (String s1) -> !Checker.isLong(s1) || Long.parseLong(s1) > 610);

            temp[2] = makeValidationCycle(commandReader, 2, "Введите координату Y: ",
                    "Ошибка! Координата должна быть числом большим -237.",
                    (String s1) -> !Checker.isFloat(s1) || Float.parseFloat(s1) <= -237);

            temp[3] = makeValidationCycle(commandReader, 3, "Введите кол.во студентов: ",
                    "Ошибка! Кол.во студентов число большее 0",
                    (String s1) -> !Checker.isInteger(s1) || Integer.parseInt(s1) <= 0);

            temp[4] = makeValidationCycle(commandReader, 4, "Введите кол.во исключенных студентов: ",
                    "Ошибка! Кол.во исключенных студентов должна быть числом большим 0.",
                    (String s1) -> !Checker.isFloat(s1) || Integer.parseInt(s1) <= 0);

            temp[5] = makeValidationCycle(commandReader, 5, FormOfEducation.ViewToString() + " \nВведите форму обучения: ",
                    "Ошибка! Форма обучения недопустима",
                    (String s1) -> !Checker.isFormOfEducation(s1));

            temp[6] = makeValidationCycle(commandReader, 6, Semester.ViewToString() + " \nВведите семестр: ",
                    "Ошибка! Семестр недопустим",
                    (String s1) -> !Checker.isSemester(s1));

            temp[7] = makeValidationCycle(commandReader, 7, "Введите Имя админа: ",
                    "Ошибка! Имя не пустое",
                    Checker::isNotString);

            temp[8] = makeValidationCycle(commandReader, 8, "Введите Рост админа: ",
                    "Ошибка! Рост чиcло > 0",
                    (String s1) -> !Checker.isInteger(s1) || Integer.parseInt(s1) <= 0);

            temp[9] = makeValidationCycle(commandReader, 9, Color.ViewToString() + "\nВведите Цвет глаз админа: ",
                    "Ошибка! Недопустимый выбор",
                    (String s1) -> !Checker.isColor(s1));

            temp[10] = makeValidationCycle(commandReader, 10, Color.ViewToString() + "\nВведите Цвет волос админа: ",
                    "Ошибка! Недопустимый выбор",
                    (String s1) -> !Checker.isColor(s1));

            temp[11] = makeValidationCycle(commandReader, 11, Country.ViewToString() + "\nВведите национальность админа: ",
                    "Ошибка! Имя не пустое",
                    (String s1) -> !Checker.isCountry(s1));

            temp[12] = makeValidationCycle(commandReader, 12, "Введите координату X локации админа: ",
                    "Ошибка! X число",
                    (String s1) -> !Checker.isInteger(s1) || Integer.parseInt(s1) < 0);

            temp[13] = makeValidationCycle(commandReader, 13, "Введите координату Y локации админа: ",
                    "Ошибка! Y число",
                    (String s1) -> !Checker.isFloat(s1) || Float.parseFloat(s1) < 0);

            temp[14] = makeValidationCycle(commandReader, 14, "Введите название локации админа: ",
                    "Ошибка! Название не должно быть пустым",
                    Checker::isNotString);

            this.studyGroup = new StudyGroup(
                    temp[0],
                    new Coordinates(
                            Long.parseLong(temp[1]),
                            Float.parseFloat(temp[2])
                    ),
                    Integer.parseInt(temp[3]), Integer.parseInt(temp[4]),
                    FormOfEducation.valueOf(temp[5]), Semester.valueOf(temp[6]),
                    new Person(temp[7], Integer.parseInt(temp[8]),
                            Color.valueOf(temp[9]), Color.valueOf(temp[10]),
                            Country.valueOf(temp[11]),
                            new Location(
                                    Integer.parseInt(temp[12]),
                                    Float.parseFloat(temp[13]),
                                    temp[14]
                            )
                    )
            );

            this.argument = argument;
            return true;
        } catch (NoSuchElementException ex){
            System.out.println();
            System.out.println("Ошибка! Отсутствие ожидаемого аргумента.");
        } catch (IllegalArgumentException ex) {
            System.out.println();
            System.out.println("Ошибка! Отсутствует ключ.");
        }
        return false;
    }
}