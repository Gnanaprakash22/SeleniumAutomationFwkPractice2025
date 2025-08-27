package com.practice.web;

import com.practice.web.driver.Driver;
import org.testng.annotations.*;

public class BaseTest {

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser){
        Driver.initDriver(browser);
    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }
}
