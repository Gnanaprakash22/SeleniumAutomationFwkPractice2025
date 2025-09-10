package com.practice.web.dataproviders;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class LoginDataProvider {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] testData() {
        Map<String, String> login1 = new HashMap<>();
        login1.put(EMAIL, "johndoe@gmail.com");
        login1.put(PASSWORD, "email@123");

        Map<String, String> login2 = new HashMap<>();
        login2.put(EMAIL, "tomhanks@gmail.com");
        login2.put(PASSWORD, "tester@123");

        return new Object[][]{
                {login1}, {login2}
        };
    }
}

