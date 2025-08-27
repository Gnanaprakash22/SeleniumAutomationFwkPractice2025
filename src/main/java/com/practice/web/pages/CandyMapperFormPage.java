package com.practice.web.pages;

import com.practice.web.enums.WaitType;
import org.openqa.selenium.By;

import static com.practice.web.utils.SeleniumUtils.*;

public class CandyMapperFormPage {

    private static final By NAME = By.cssSelector("#input16");
    private static final By CLOSE_ICON = By.id("popup-widget307423-close-icon");

    public CandyMapperFormPage closePopup(){
        click(CLOSE_ICON, WaitType.CLICKABLE);
        return this;
    }

    public CandyMapperFormPage enterName(){
        scrollToElementUsingActions(NAME);
        sendKeys(NAME,"Tom");
        return this;
    }

}
