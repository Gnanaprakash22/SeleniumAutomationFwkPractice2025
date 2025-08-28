package com.practice.web.dataproviders;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class UserDataProvider {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String MESSAGE = "message";

    @DataProvider(name = "userData" , parallel = true)
    public Object[][] testData() {
        Map<String, String> user1 = new HashMap<>();
        user1.put(FIRST_NAME, "John");
        user1.put(LAST_NAME, "Doe");
        user1.put(EMAIL, "johndoe@gmail.com");
        user1.put(PHONE_NUMBER, "1234567890");
        user1.put(MESSAGE, "Nice");

        Map<String, String> user2 = new HashMap<>();
        user2.put(FIRST_NAME, "Alex");
        user2.put(LAST_NAME, "Hales");
        user2.put(EMAIL, "alexhales@gmail.com");
        user2.put(PHONE_NUMBER, "1234567891");
        user2.put(MESSAGE, "Great");

        Map<String, String> user3 = new HashMap<>();
        user3.put(FIRST_NAME, "Lily");
        user3.put(LAST_NAME, "Rhodes");
        user3.put(EMAIL, "lilyrhodes@gmail.com");
        user3.put(PHONE_NUMBER, "1234567892");
        user3.put(MESSAGE, "Bad");

        return new Object[][]{
                {user1},
                {user2},
                {user3}
        };
    }
}
