package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.exceptions.NotEnoughDataException;

/**
 * Created by Александр on 18.12.2016.
 */
class ArgumentParser {
    private int currentIndex;
    private String[] strArray;

    public ArgumentParser(String[] strArray) {
        currentIndex = 0;
        this.strArray = strArray;
    }

    public String getNextStringWithoutNumbers() throws NotEnoughDataException {
        StringBuilder result = new StringBuilder();
        while (currentIndex < strArray.length && !isNextNumber()) {
            result.append(strArray[currentIndex]).append(" ");
            currentIndex++;
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        } else {
            throw new NotEnoughDataException("Ошибка ввода. Ожидается строка");
        }
    }

    public String getNextString() throws NotEnoughDataException {
        StringBuilder result = new StringBuilder();
        while (currentIndex < strArray.length) {
            result.append(strArray[currentIndex]).append(" ");
            currentIndex++;
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        } else {
            throw new NotEnoughDataException("Ошибка ввода. Ожидается строка");
        }
    }

    public double getNextDouble() throws NotEnoughDataException {
        if (currentIndex < strArray.length && isNextNumber())
            return Double.parseDouble(strArray[currentIndex++]);
        else
            throw new NotEnoughDataException("Ошибка. Ожидается число");
    }

    private boolean isNextNumber() {
        return strArray[currentIndex].matches("(-?[0-9]+(\\.[0-9]+)?)+");
    }
}
