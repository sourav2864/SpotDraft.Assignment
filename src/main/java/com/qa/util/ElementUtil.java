package com.qa.util;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumber.listener.Reporter;
import com.pages.*;
import com.qa.factory.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/*This class is used to handle all the web element related actions*/

public class ElementUtil {

    public String alertPopUpGetText() {
        return (DriverFactory.getDriver().switchTo().alert().getText());
    }

    public void alertPopUpAccept() {
        Alert alert = DriverFactory.getDriver().switchTo().alert();
        alert.accept();
    }

    public static void waitFor(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElementDisplayed(WebElement element) {
        (new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60))).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementClickable(WebElement element) {
        (new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60))).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void javaScriptScrollToEnd() {
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void waitForListOfElementDisplayed(List<WebElement> element) {
        (new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60))).until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void clickOnWebElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].setAttribute('style', 'background: lightskyblue; border: 2px solid red;');",
                element);
        waitFor(1000);
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public Boolean verifyTitle(String expectedTitle) {
        return DriverFactory.getDriver().getTitle().contains(expectedTitle);
    }

    public Boolean verifyUrl(String expectedUrl) {
        return DriverFactory.getDriver().getCurrentUrl().contains(expectedUrl);
    }

    public void loggPrint(String str) {
        ExtentCucumberAdapter.addTestStepLog(str);
    }

    public void verifyBookNameWithURLinPDP(String bookName) {
        String s1 = bookName.replace(" ", "_").toLowerCase().trim();
        Boolean b = DriverFactory.getDriver().getCurrentUrl().contains(s1);
        if(b=false){
            Assert.fail(bookName+" is not matched with URL");
        }
    }

    public void escapeKey() {
        Actions action = new Actions(DriverFactory.getDriver());
        action.sendKeys(Keys.ESCAPE).build().perform();
        ;
    }
}