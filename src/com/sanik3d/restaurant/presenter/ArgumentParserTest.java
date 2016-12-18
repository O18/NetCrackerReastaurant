package com.sanik3d.restaurant.presenter;

import com.sanik3d.restaurant.exceptions.NotEnoughDataException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Александр on 18.12.2016.
 */
public class ArgumentParserTest {

    @Test
    public void testNumeric() throws NotEnoughDataException {
        String[] arrStrings = new String[]{"a", "b", "c", "200", "d"};
        ArgumentParser parser = new ArgumentParser(arrStrings);

        assertEquals("a b c", parser.getNextStringWithoutNumbers());
        assertEquals(200d, parser.getNextDouble(), 0);
        assertEquals("d", parser.getNextStringWithoutNumbers());
    }
}