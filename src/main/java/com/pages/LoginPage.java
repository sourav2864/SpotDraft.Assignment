package com.pages;

import com.qa.factory.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(xpath = "//button[contains(text(),'Sign in with email')]")
    private WebElement signInWithEmailButton;

    public WebElement getSignInWithEmailButton() {
        return signInWithEmailButton;
    }
}
