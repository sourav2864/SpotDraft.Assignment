package com.pages;

import com.qa.factory.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FrontPage {
    public FrontPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
    @FindBy(xpath = "//div[@id='signIn']//a")
    private WebElement signInCTA;
    public WebElement getSignInCTA() {
        return signInCTA;
    }

}
