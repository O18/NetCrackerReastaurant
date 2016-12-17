package com.sanik3d.restaurant.view;


import java.util.Arrays;

/**
 * Created by 1 on 13.11.2016.
 */
public class Parser {

    private StringBuffer sb;

    public String getCommand(String inString) {
        String[] dividedInString = inString.split(" ");
        return dividedInString[0];
    }

    private static boolean isNum(String string) throws NumberFormatException {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String[] getArgs(String inString) {
        String[] dividedString = inString.replaceAll(" +", " ").trim().split(" ");
        return Arrays.copyOfRange(dividedString, 1, dividedString.length);
//        String[] arrayOfStrings = inString.split(" ");
//        String command = getCommand(inString);
//        if (command.equals("add_dish")) {
//            String[] dishesStrings = new String[3];
//            int i;
//            dishesStrings[0] = "";
//            dishesStrings[1] = "";
//            dishesStrings[2] = "";
//            for (i = 1; !isNum(arrayOfStrings[i]); i++)
//                dishesStrings[0] += (arrayOfStrings[i] + ' ');
//            sb.append(dishesStrings[0]);
//            sb.deleteCharAt(dishesStrings[0].length() - 1);
//            dishesStrings[0] = sb.toString();
//            dishesStrings[1] = arrayOfStrings[i];
//            for (++i; i < arrayOfStrings.length - 1; i++)
//                dishesStrings[2] += (arrayOfStrings[i] + ' ');
//            dishesStrings[2] += arrayOfStrings[arrayOfStrings.length - 1];
//            return dishesStrings;
//        }
//        else {
//            String[] dishesStrings = new String[1];
//            dishesStrings[0] = "";
//            for (int i = 1; i < arrayOfStrings.length - 1; i++)
//                dishesStrings[0] += (arrayOfStrings[i] + ' ');
//            dishesStrings[0] += arrayOfStrings[arrayOfStrings.length - 1];
//            return dishesStrings;
//        }
    }
}
