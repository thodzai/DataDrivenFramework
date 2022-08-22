package vn.thodzai.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import vn.thodzai.base.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestUtil extends TestBase {

    public static String screenshotName;

    public static void captureScreenshot() throws IOException {

        File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        screenshotName = new Date().toString().replaceAll(":| ", "_") + ".jpg";

        FileUtils.copyFile(screenshotFile, new File(screenshotPath + screenshotName));
//        FileUtils.copyFile(screenshotFile, new File(".\\reports\\" + screenshotName));

    }

}
