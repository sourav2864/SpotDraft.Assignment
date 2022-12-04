package com.pages;

import com.qa.factory.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    public SignInPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
    @FindBy(id = "ap_email")
    private WebElement emailInputBox;
    @FindBy(id = "ap_password")
    private WebElement passwordInputBox;
    @FindBy(id = "signInSubmit")
    private WebElement signInButton;

    public WebElement getEmailInputBox() {
        return emailInputBox;
    }
    public WebElement getPasswordInputBox() {
        return passwordInputBox;
    }
    public WebElement getSignInButton() {
        return signInButton;
    }

}
