package Registration;

public class RegistrationBody {

    public static String registrationBody(String firstName, String lastName) {
        return "{\n" +
                "  \"firstName\": \""+firstName+"\",\n" +
                "  \"lastName\": \""+lastName+"\",\n" +
                "  \"email\": \"mail@mail.com\",\n" +
                "  \"password\": \"stringst\",\n" +
                "  \"gender\": \"FEMALE\",\n" +
                "  \"dateOfBirth\": \"2022-02-10\",\n" +
                "  \"zipCode\": \"string\",\n" +
                "  \"insuranceNumber\": \"string\"\n" +
                "}";
    }

    public static String mandatoryRegistrationBody(String firstName, String lastName) {
        return "{\n" +
                "  \"firstName\": \""+firstName+"\",\n" +
                "  \"lastName\": \""+lastName+"\",\n" +
                "  \"email\": \"mail@mail.com\",\n" +
                "  \"password\": \"stringst\"" +
                "}";
    }

}
