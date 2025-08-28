package com.practice.web;

import com.practice.web.constants.Constants;
import com.practice.web.dataproviders.UserDataProvider;
import com.practice.web.pages.CandyMapperFormPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class CandyMapperFormTest extends BaseTest {

    @Test(dataProvider = "userData",dataProviderClass = UserDataProvider.class)
    public void fillCandyFormMapperTest(Map<String, String> userData) {
        CandyMapperFormPage page = new CandyMapperFormPage();
        page.closePopup().enterFirstName(userData.get("firstName"))
                .enterLastName(userData.get("lastName")).enterEmail(userData.get("email"))
                .enterPhoneNumber(userData.get("phoneNumber")).enterMessage(userData.get("message"))
                .submitForm();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getThankYouMsg(), Constants.THANKYOU_MSG);
        softAssert.assertAll();
    }
}
