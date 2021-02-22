package com.vvicee.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void validateNameTest(){
        boolean actualValid = Validator.validateName("Ivan");
        boolean actualInvalid = Validator.validateName("@@@@@@@@@");

        assertTrue(actualValid);
        assertFalse(actualInvalid);
    }

    @Test
    public void validatePasswordTest(){
        boolean actualValid = Validator.validatePassword("ivan12345");
        boolean actualInvalid = Validator.validatePassword("1");

        assertTrue(actualValid);
        assertFalse(actualInvalid);
    }

    @Test
    public void validateEmailTest(){
        boolean actualValid = Validator.validateEmail("ivan@mail.ru");
        boolean actualInvalid = Validator.validateEmail("1");

        assertTrue(actualValid);
        assertFalse(actualInvalid);
    }

    @Test
    public void validatePriceTest(){
        boolean actualValid = Validator.validatePrice(123);
        boolean actualInvalid = Validator.validatePrice(-123);

        assertTrue(actualValid);
        assertFalse(actualInvalid);
    }

    @Test
    public void validateNameAndSurnameTest(){
        boolean actualValid = Validator.validateName("Ivan", "Ivanov");
        boolean actualInvalid = Validator.validateName("-123", "^&%$");

        assertTrue(actualValid);
        assertFalse(actualInvalid);
    }

    @Test
    public void validateComparePasswordTest(){
        boolean actualValid = Validator.comparePasswords("12345", "12345");
        boolean actualInvalid = Validator.comparePasswords("-12345", "12345");

        assertTrue(actualValid);
        assertFalse(actualInvalid);
    }
}
