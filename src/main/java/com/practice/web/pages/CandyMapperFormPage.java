package com.practice.web.pages;

import com.practice.web.enums.Actions;
import com.practice.web.enums.Elements;
import com.practice.web.enums.WaitType;
import com.practice.web.utils.LoggerUtils;
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
        LoggerUtils.browserAction(Actions.CLICK.name(), Elements.CLOSE_BUTTON.name());
        return this;
    }

    public CandyMapperFormPage enterFirstName(String firstName){
        scrollToElementUsingActions(FIRST_NAME);
        LoggerUtils.browserAction(Actions.SCROLL.name(), Elements.FIRST_NAME.name());
        sendKeys(FIRST_NAME,firstName);
        LoggerUtils.browserAction(Actions.SENDKEYS.name(), Elements.FIRST_NAME.name());
        return this;
    }

    public CandyMapperFormPage enterLastName(String lastName){
        sendKeys(LAST_NAME,lastName);
        LoggerUtils.browserAction(Actions.SENDKEYS.name(), Elements.LAST_NAME.name());
        return this;
    }

    public CandyMapperFormPage enterEmail(String email){
        sendKeys(EMAIL,email);
        LoggerUtils.browserAction(Actions.SENDKEYS.name(), Elements.EMAIL.name());
        return this;
    }

    public CandyMapperFormPage enterPhoneNumber(String phoneNumber){
        sendKeys(PHONE_NUMBER,phoneNumber);
        LoggerUtils.browserAction(Actions.SENDKEYS.name(), Elements.PHONE_NUMBER.name());
        return this;
    }

    public CandyMapperFormPage enterMessage(String message){
        sendKeys(MESSAGE,message);
        LoggerUtils.browserAction(Actions.SENDKEYS.name(), Elements.MESSAGE.name());
        return this;
    }

    public CandyMapperFormPage submitForm(){
        scrollToElementUsingActions(SUBMIT_BUTTON);
        LoggerUtils.browserAction(Actions.SCROLL.name(), Elements.SUBMIT_BUTTON.name());
        click(SUBMIT_BUTTON,WaitType.CLICKABLE);
        LoggerUtils.browserAction(Actions.CLICK.name(), Elements.SUBMIT_BUTTON.name());
        return this;
    }

    public String getThankYouMsg(){
        LoggerUtils.browserAction(Actions.GETTEXT.name(), Elements.THANKYOU_MSG.name());
        return getText(THANKYOU_MSG);
    }

}
