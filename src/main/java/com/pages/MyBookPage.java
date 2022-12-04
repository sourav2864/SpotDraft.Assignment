package com.pages;

import com.qa.factory.DriverFactory;
import com.qa.util.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class MyBookPage extends ElementUtil {
    public MyBookPage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(xpath = "//div[@id='header']//h1//a")
    private WebElement myBooksHeadTitle;

    @FindBy(xpath = "//tbody[@id='booksBody']//td[@class='field title']//a")
    private List<WebElement> bookNamesList;

    @FindBy(xpath = "//div[@class='box noticeBox']//div")
    private WebElement noticeBoxMsg;

    public WebElement getMyBooksHeadTitle() {
        return myBooksHeadTitle;
    }

    public List<WebElement> getBookNamesList() {
        return bookNamesList;
    }

    public WebElement getNoticeBoxMsg() {
        return noticeBoxMsg;
    }

    public void it_should_get_added_in_to_MyBookList(String bookName) {
        Boolean b = false;
        for (WebElement e : bookNamesList) {
            if (e.getText().trim().toLowerCase().equals(bookName)) {
                b = true;
                break;
            }
        }
        if (b == false) {
            Assert.fail(bookName + " is not added to My Book List");
        }
    }

    public void i_removed_it_from_book_list(String bookName) {
        String s1 = "//td[@class='field title']//a[@title='";
        String s2 = "']/ancestor::tr//td[@class='field actions']//a[@class='actionLinkLite smallText deleteLink']";
        clickOnWebElement(DriverFactory.getDriver().findElement(By.xpath(s1 + bookName + s2)));
        waitFor(1500);
        alertPopUpAccept();
    }

}
