package com.practice.web;

import com.practice.web.constants.Constants;
import com.practice.web.pages.CandyMapperFormPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CandyMapperFormTest extends BaseTest{

    @Test
    public void fillCandyFormMapperTest(){
        CandyMapperFormPage page = new CandyMapperFormPage();
        page.closePopup().enterFirstName()
                .enterLastName().enterEmail()
                .enterPhoneNumber().enterMessage()
                .submitForm();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getThankYouMsg(), Constants.THANKYOU_MSG);
        softAssert.assertAll();
    }
}
