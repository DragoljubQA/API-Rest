package LibraryAPI;

public class bPayloadLibrary {

    public static String addBook() {
        String payload = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\"bhehjcd\",\n" +
                "\"aisle\":\"22457\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n";
        return payload;
    }
}
