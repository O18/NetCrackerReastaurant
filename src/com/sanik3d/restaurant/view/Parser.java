package com.sanik3d.restaurant.view;

import com.sanik3d.restaurant.eventbus.event.Event;

import java.util.HashMap;

/**
 * Created by 1 on 13.11.2016.
 */
public class Parser {

    StringBuffer sb = new StringBuffer();
    public String getCommand (String inString) {
        String[] strings = inString.split(" ");
        return strings[0];
    }
    private static boolean isNum(String string) throws NumberFormatException{
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    public String[] getArgs(String inString) {
        /*HashMap<String,Integer> map = new HashMap<>();
        map.put("adddish",3);
        map.put("addcategory", 1);
        map.put("deletedish",1);
        map.put("deletecategory",1);
        map.put("load", 1);
        map.put("save", 1);
        String[] strings = new String[map.get(getCommand(inString))];*/
        String[] arrayOfStrings = inString.split(" ");
        if (getCommand(inString)=="adddish") {
            String[] strings = new String[3];
            int i;
            for (i = 1; !isNum(arrayOfStrings[i]) ;i++)
                strings[0] += (arrayOfStrings[i] + ' ');
            sb.append(strings[0]);
            sb.deleteCharAt(strings[0].length()-1);
            strings[0] = sb.toString();
            strings[1] = arrayOfStrings[i];
            for ( ++i;i<arrayOfStrings.length-1;i++ )
                strings[2]+=(arrayOfStrings[i]+' ');
            strings[2] += arrayOfStrings[arrayOfStrings.length-1];
            return strings;
        }
        String[] strings = new String[1];
        for (int i = 1; i<arrayOfStrings.length-1;i++)
            strings[0]+=(arrayOfStrings[i]+' ');
        strings[0] += arrayOfStrings[arrayOfStrings.length-1];
        return strings;
    }
}
