package parallel;

import com.pages.*;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.ElementUtil;
import io.cucumber.java.en.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class Want_to_Read_Steps extends ElementUtil {
    FrontPage fp = new FrontPage();
    LoginPage lp = new LoginPage();
    SignInPage sp = new SignInPage();
    HomePage hp = new HomePage();
    PDPpage pdp = new PDPpage();
    MyBookPage mbp = new MyBookPage();
    String bookName;

    @Given("^I Login to \"([^\"]*)\" with valid credentials (.+) and (.+)$")
    public void iLoginTDNEpUTHQoQUJMHLrErGJyHg89uy71MyuHword(String url, String un, String pwd) throws IOException {
        waitForElementDisplayed(fp.getSignInCTA());
        Assert.assertTrue(verifyUrl(url), "front page URL didn't match");
        Assert.assertTrue(verifyTitle(ConfigReader.getData("appTitle")), "front page title didn't match");
        loggPrint("User navigated to Front page Successfully");
        clickOnWebElement(fp.getSignInCTA());
        waitForElementDisplayed(lp.getSignInWithEmailButton());
        Assert.assertTrue(verifyUrl(ConfigReader.getData("loginPageUrl")), "Login page URL didn't match");
        loggPrint("User navigated to Login page Successfully");
        clickOnWebElement(lp.getSignInWithEmailButton());
        waitForElementDisplayed(sp.getEmailInputBox());
        Assert.assertTrue(verifyUrl(ConfigReader.getData("signInPageUrl")), "Sign In page URL didn't match");
        loggPrint("User navigated to Sign In page Successfully");
        sp.getEmailInputBox().sendKeys(un.trim());
        sp.getPasswordInputBox().sendKeys(pwd.trim());
        clickOnWebElement(sp.getSignInButton());
        Boolean b = hp.getMyBooksHeaderMenu().isDisplayed();
        if (b = false) {
            Assert.fail("User unable to navigate to home page");
        }
        loggPrint("User Logged in to \"goodreads.com\" with valid credentials Successfully " + "\n" +
                "and navigated to Home page Successfully");
    }

    @And("^Search for a specific book title in searchBox (.+)$")
    public void searchForASpecificBookTitleInSearchBoxBookName(String b_Name) {
        bookName = b_Name;
        hp.getSearchBoxTop().sendKeys(bookName);
        waitForListOfElementDisplayed(hp.getAutoSuggestionSearchList());
        loggPrint("User Searched for a specific book title in searchBox");
    }

    @Then("navigate to Product details Page of that book")
    public void navigate_to_product_details_page_of_that_book() throws IOException {
        for (WebElement ele : hp.getAutoSuggestionSearchList()) {
            if (ele.getText().trim().toLowerCase().equals(bookName.toLowerCase())) {
                loggPrint(bookName + " book is displayed successfully");
                clickOnWebElement(ele);
                break;
            }
        }
        waitFor(2500);
        escapeKey();
        Assert.assertTrue(verifyUrl(ConfigReader.getData("pdpPageUrl")), "User unable to navigate to PDP page");
        verifyBookNameWithURLinPDP(bookName.toLowerCase());
        loggPrint("User navigated to " + bookName + " Successfully");
    }

    @When("I mark it as {string}")
    public void i_mark_it_as(String wantReadBtn) {
        pdp.i_mark_it_asWantToRead(wantReadBtn);
        loggPrint("User marked it as \"want to read\" Successfully");
    }

    @Then("it should get added in to {string}")
    public void it_should_get_added_in_to(String str) {
        waitFor(3000);
        clickOnWebElement(hp.getMyBooksHeaderMenu());
        waitForElementDisplayed(mbp.getMyBooksHeadTitle());
        Assert.assertEquals(mbp.getMyBooksHeadTitle().getText().trim(), str,
                str + " title didn't match");
        waitForListOfElementDisplayed(mbp.getBookNamesList());
        mbp.it_should_get_added_in_to_MyBookList(bookName.toLowerCase());
        loggPrint(bookName + " got added in to \"My Books\" list");
    }

    @When("I removed it from Book list")
    public void i_removed_it_from_book_list() throws IOException {
        mbp.i_removed_it_from_book_list(bookName);
        String s1 = mbp.getNoticeBoxMsg().getText().trim();
        String s2 = bookName + " " + ConfigReader.getData("removeMsg");
        Assert.assertEquals(s1, s2, "After removed notice message is not appearing correctly");
        loggPrint(bookName + " is removed");
    }

    @Then("It should get removed from the Book list")
    public void it_should_get_removed_from_the_book_list() {
        Boolean b = true;
        for (WebElement e : mbp.getBookNamesList()) {
            if (e.getText().trim().toLowerCase().equals(bookName)) {
                b = false;
                break;
            }
        }
        if (b == false) {
            Assert.fail(bookName + " is still exist in Book List");
        }
        loggPrint(bookName + " is removed from Book list Successfully");
    }

    @Then("I logged out from the application")
    public void i_logged_out_from_the_apllication() {
        clickOnWebElement(hp.getProfileMenu());
        waitForElementDisplayed(hp.getSignOut_CTA());
        waitForElementClickable(hp.getSignOut_CTA());
        clickOnWebElement(hp.getSignOut_CTA());
        waitForElementDisplayed(fp.getSignInCTA());
        Assert.assertTrue(fp.getSignInCTA().isDisplayed(), "User unable to signOut");
        loggPrint("User logged out from the application Successfully");
    }
}
