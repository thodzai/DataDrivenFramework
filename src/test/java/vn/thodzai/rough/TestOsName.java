package vn.thodzai.rough;

public class TestOsName {

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("sun.arch.data.model"));
    }

}
