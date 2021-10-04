package tools;

import java.util.Scanner;

public class Validator {
    /**
     * Метод для валидации входных данных
     */
    public static String makeValidationCycle(Scanner commandReader, int index, String start, String error, CheckValid check) {
        String value;
        System.out.println(start);
        value = commandReader.nextLine();
        while (check.check(value)) {
            System.out.println(error);
            System.out.println(start);
            value = commandReader.nextLine();
        }
        return value;
    }
}
