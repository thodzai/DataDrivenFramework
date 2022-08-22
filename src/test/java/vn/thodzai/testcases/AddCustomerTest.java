package vn.thodzai.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import vn.thodzai.base.TestBase;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void addCustomer(String firstName, String lastName, String postCode, String popUpMessage) throws InterruptedException {

//        webDriver.findElement(By.cssSelector(OR.getProperty("bankManagerLoginBtn_CSS"))).click();
        webDriver.findElement(By.cssSelector(OR.getProperty("addCustomerBtn_CSS"))).click();
        webDriver.findElement(By.cssSelector(OR.getProperty("firstNameTxt_CSS"))).sendKeys(firstName);
        webDriver.findElement(By.xpath(OR.getProperty("lastNameTxt_XPATH"))).sendKeys(lastName);
        webDriver.findElement(By.cssSelector(OR.getProperty("postCodeTxt_CSS"))).sendKeys(postCode);
        webDriver.findElement(By.cssSelector(OR.getProperty("addCustomerSubmitBtn_CSS"))).click();

        Alert alert = webDriverWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(popUpMessage));
        alert.accept();

    }

    @DataProvider
    public Object[][] getData() {

        String sheetName = "AddCustomerTest";

        int rows = excelReader.getRowCount(sheetName);
        int cols = excelReader.getColumnCount(sheetName);

        Object[][] data = new Object[rows - 1][cols];

        for (int rowNum = 2; rowNum <= rows; rowNum++) {
            for (int colNum = 0; colNum < cols; colNum++) {

                data[rowNum - 2][colNum] = excelReader.getCellData(sheetName, colNum, rowNum);

            }
        }

        return data;

    }

}
