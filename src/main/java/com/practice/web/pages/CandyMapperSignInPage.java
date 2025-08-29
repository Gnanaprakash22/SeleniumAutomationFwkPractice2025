package com.practice.web.pages;

import com.practice.web.enums.Actions;
import com.practice.web.enums.Elements;
import com.practice.web.enums.WaitType;
import com.practice.web.utils.LoggerUtils;
import org.openqa.selenium.By;
import static com.practice.web.utils.SeleniumUtils.*;

public class CandyMapperSignInPage  {

    private static final By EMAIL = By.cssSelector("input[data-aid='MEMBERSHIP_SSO_LOGIN_EMAIL']");
    private static final By PASSWORD = By.cssSelector("input[data-aid='MEMBERSHIP_SSO_LOGIN_PASSWORD']");
    private static final By SIGN_IN = By.cssSelector("button[data-aid='MEMBERSHIP_SSO_SUBMIT']");
    private static final By FORM_ERROR = By.cssSelector("p[data-ux='FormError']");

    public static CandyMapperSignInPage getInstance(){
        return new CandyMapperSignInPage();
    }

    public CandyMapperSignInPage login(String email,String password){
        sendKeys(EMAIL,email);
        LoggerUtils.browserAction(Actions.SENDKEYS.name(), Elements.EMAIL.name());
        sendKeys(PASSWORD,password);
        LoggerUtils.browserAction(Actions.SENDKEYS.name(), Elements.PASSWORD.name());
        click(SIGN_IN, WaitType.CLICKABLE);
        LoggerUtils.browserAction(Actions.CLICK.name(), Elements.SIGN_IN.name());
        return this;
    }

    public String getFormErrorText(){
        LoggerUtils.browserAction(Actions.GETTEXT.name(), Elements.FORM_ERROR.name());
        return getText(FORM_ERROR);
    }



}
