package com.practice.web;

import com.practice.web.constants.PageConstants;
import com.practice.web.dataproviders.LoginDataProvider;
import com.practice.web.dataproviders.UserDataProvider;
import com.practice.web.pages.CandyMapperFormPage;
import com.practice.web.pages.CandyMapperSignInPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class CandyMapperFormTest extends BaseTest {

    @Test(dataProvider = "userData",dataProviderClass = UserDataProvider.class)
    public void fillCandyFormMapperTest(Map<String, String> userData) {
        CandyMapperFormPage page = CandyMapperFormPage.getInstance();
        page.closePopup().enterFirstName(userData.get("firstName"))
                .enterLastName(userData.get("lastName")).enterEmail(userData.get("email"))
                .enterPhoneNumber(userData.get("phoneNumber")).enterMessage(userData.get("message"))
                .submitForm();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getThankYouMsg(), PageConstants.THANKYOU_MSG);
        softAssert.assertAll();
    }

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void loginTest(Map<String, String> loginData){
        CandyMapperFormPage page = CandyMapperFormPage.getInstance();
        page.closePopup().clickOnSignInOption().login(loginData.get("email"),loginData.get("password"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(CandyMapperSignInPage.getInstance().getFormErrorText(), PageConstants.SIGN_IN_ERROR);
        softAssert.assertAll();
    }
}
