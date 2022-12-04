package com.pages;

import com.qa.factory.DriverFactory;
import com.qa.util.ElementUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PDPpage extends ElementUtil {
    public PDPpage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='BookPage__leftColumn']//button//span[text()='Want to read']")
    private WebElement wantToReadBtn1;

    @FindBy(xpath = "//input[@name='authenticity_token']/..//span[text()='Want to Read']")
    private WebElement wantToReadBtn2;

    @FindBy(xpath = "//div[@class='Overlay Overlay--floating']//i[@class='Icon CloseIcon']")
    private WebElement oneNewBookPagePopUpCloseCTA;


    @FindBy(xpath = "//div[@class='BookPage__rightColumn']//h1")
    private WebElement bookTitleOnPDP1;

    @FindBy(xpath = "//div[@id='metacol']//h1[@id='bookTitle']")
    private WebElement bookTitleOnPDP2;

    public void i_mark_it_asWantToRead(String wantReadBtn) {
        Boolean b = false;
        try {
            if (bookTitleOnPDP2.isDisplayed()) {
                b = true;
            }
        } catch (Exception e) {
        }
        if (b == true) {
            scrollToElement(bookTitleOnPDP2);
            Assert.assertEquals(wantToReadBtn2.getText().trim().toLowerCase(), wantReadBtn,
                    "Want to read button name is not matching");
            clickOnWebElement(wantToReadBtn2);
        } else {
            scrollToElement(bookTitleOnPDP1);
            Assert.assertEquals(wantToReadBtn1.getText().trim().toLowerCase(), wantReadBtn,
                    "Want to read button name is not matching");
            clickOnWebElement(wantToReadBtn1);
        }
    }
}
