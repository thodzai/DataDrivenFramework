package vn.thodzai.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import vn.thodzai.base.TestBase;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {

        LOGGER.debug("Inside Login Test");

        webDriver.findElement(By.cssSelector(OR.getProperty("bankManagerLoginBtn_CSS"))).click();

        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustomerBtn_CSS"))), "Login not successful");

        LOGGER.debug("Login Successfully Executed");

    }

}
