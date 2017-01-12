package com.o18.restaurant.view;


import java.util.Arrays;

/**
 * Created by 1 on 13.11.2016.
 */
public class Parser {
    public String getCommand(String inString) {
        String[] dividedInString = inString.split(" ");
        return dividedInString[0];
    }

    public String[] getArgs(String inString) {
        String[] dividedString = inString.replaceAll(" +", " ").trim().split(" ");
        return Arrays.copyOfRange(dividedString, 1, dividedString.length);
    }
}
