package com.practice.web;

import com.practice.web.driver.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeMethod
    public void setUp(){
        Driver.initDriver();
    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }
}
