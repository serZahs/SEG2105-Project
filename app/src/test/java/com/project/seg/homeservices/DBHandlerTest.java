package com.project.seg.homeservices;

import org.junit.Test;
import static org.junit.Assert.*;

public class DBHandlerTest {
    // Can be enabled once the methods isValidEmail(), isValidService()
    // and isValidRate() in DBHandler are declared static
    /*@Test
    public void emailFormatIsValid() {
        boolean valid = DBHandler.isValidEmail("localpart@domain.com");
        assertTrue(valid);
    }

    @Test
    public void emailLengthIsValid() {
        boolean valid = DBHandler.isValidEmail("em@domain.com");
        assertFalse(valid);
    }

    @Test
    public void emailIsMissingDelimiter() {
        boolean valid = DBHandler.isValidEmail("localpart@domaincom");
        assertFalse(valid);
    }

    @Test
    public void serviceIsValid() {
        boolean valid = DBHandler.isValidService("Handyman Services");
        assertTrue(valid);
        boolean valid2 = DBHandler.isValidService("Gardening");
        assertFalse(valid2);
    }

    @Test
    public void rateIsValid() {
        boolean valid = DBHandler.isValidRate(-55.5);
        assertFalse(valid);
    }*/
}
