package com.practice.web;

import com.practice.web.pages.CandyMapperFormPage;
import org.testng.annotations.Test;

public class CandyMapperFormTest extends BaseTest{

    @Test
    public void fillCandyFormMapperTest(){
        CandyMapperFormPage page = new CandyMapperFormPage();
        page.enterName();
    }
}
