package hexlet.code;

public class StringSchema {
    public void required() {
        //
    }
    public void minLength(int valMinLength) {
        if (curString.length() < valMinLength) {
            System.out.println("String has a length that is less than the minimum");
        }
    }
    public void contains(String curSubstring) {
        //
    }
    private String curString;
}