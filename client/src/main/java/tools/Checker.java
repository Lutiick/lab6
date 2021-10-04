package tools;

import content.*;

/**
 * Класс для проверки типа аргумента пользовательской команды
 */
public final class Checker {
    public static boolean isFloat(String s){
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
    public static boolean isLong(String s){
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isFormOfEducation(String s){
        try {
            FormOfEducation.valueOf(s);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isSemester(String s){
        try {
            Semester.valueOf(s);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isCountry(String s){
        try {
            Country.valueOf(s);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isColor(String s){
        try {
            Color.valueOf(s);
            return true;
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isNotString(String s) {
        return s == null || s.equals("null") || s.trim().equals("");
    }
}

