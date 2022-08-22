package vn.thodzai.rough;

import java.util.Date;

public class TestTimestamp {

    public static void main(String[] args) {
        Date date = new Date();
        String screenshotName = date.toString().replaceAll(":| ", "_") + ".jpg";
        System.out.println(screenshotName);
        System.out.println(date);
    }

}
