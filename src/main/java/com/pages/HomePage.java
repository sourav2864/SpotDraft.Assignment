package com.pages;

import com.qa.factory.DriverFactory;
import com.qa.util.ElementUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends ElementUtil {
    public HomePage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(xpath = "//input[@class='searchBox__input searchBox__input--navbar']")
    private WebElement searchBoxTop;

    @FindBy(xpath = "//a[text()='My Books']")
    private WebElement myBooksHeaderMenu;

    @FindBy(xpath = "//div[contains(@id,'bookSearchResults')]//a//div[@class='gr-book__title gr-book__title--navbar u-defaultType']")
    private List<WebElement> autoSuggestionSearchList;

    @FindBy(xpath = "//div[@class='siteHeader__personal']//a[contains(@class,'profileMenu dropdown__trigger--personalNav')]")
    private WebElement profileMenu;

    @FindBy(xpath = "//div[@class='siteHeader__personal']//li[@role='menuitem Sign out']//a")
    private WebElement signOut_CTA;

    public WebElement getSearchBoxTop() {
        return searchBoxTop;
    }
    public WebElement getMyBooksHeaderMenu() {
        return myBooksHeaderMenu;
    }
    public List<WebElement> getAutoSuggestionSearchList() {
        return autoSuggestionSearchList;
    }

    public WebElement getSignOut_CTA() {
        return signOut_CTA;
    }


    public WebElement getProfileMenu() {
        return profileMenu;
    }
}
