package com.practice.web.pages;

import com.practice.web.enums.WaitType;
import org.openqa.selenium.By;

import static com.practice.web.utils.SeleniumUtils.*;

public class CandyMapperFormPage {

    private static final By FIRST_NAME = By.cssSelector("input[data-aid='First Name']");
    private static final By LAST_NAME = By.cssSelector("input[data-aid='Last Name']");
    private static final By EMAIL = By.cssSelector("input[data-aid='CONTACT_FORM_EMAIL']");
    private static final By PHONE_NUMBER = By.cssSelector("input[data-aid='By entering a Phone Number you agree to our SMS Terms of Service']");
    private static final By MESSAGE = By.cssSelector("textarea[data-aid='CONTACT_FORM_MESSAGE']");
    private static final By CLOSE_ICON = By.cssSelector("#popup-widget307423-close-icon");
    private static final By SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    private static final By THANKYOU_MSG = By.cssSelector("div[data-aid='CONTACT_FORM_SUBMIT_SUCCESS_MESSAGE'] span");

    public CandyMapperFormPage closePopup(){
        click(CLOSE_ICON, WaitType.CLICKABLE);
        return this;
    }

    public CandyMapperFormPage enterFirstName(){
        scrollToElementUsingActions(FIRST_NAME);
        sendKeys(FIRST_NAME,"Tom");
        return this;
    }

    public CandyMapperFormPage enterLastName(){
        sendKeys(LAST_NAME,"Hanks");
        return this;
    }

    public CandyMapperFormPage enterEmail(){
        sendKeys(EMAIL,"tomhanks@yahoo.com");
        return this;
    }

    public CandyMapperFormPage enterPhoneNumber(){
        sendKeys(PHONE_NUMBER,"1234567890");
        return this;
    }

    public CandyMapperFormPage enterMessage(){
        sendKeys(MESSAGE,"Good User Experience");
        return this;
    }

    public CandyMapperFormPage submitForm(){
        scrollToElementUsingActions(SUBMIT_BUTTON);
        click(SUBMIT_BUTTON,WaitType.CLICKABLE);
        return this;
    }

    public String getThankYouMsg(){
        return getText(THANKYOU_MSG);
    }

}
