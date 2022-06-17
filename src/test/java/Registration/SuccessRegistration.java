package Registration;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SuccessRegistration {
    public static void main(String[] args) {

        RestAssured.baseURI = "http://inflamco-public-test-1904104414.us-east-1.elb.amazonaws.com";
        //Successful Registration
        String firstName = "ime";
        String lastName = "prezime";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(RegistrationBody.registrationBody(firstName, lastName))
                .when().post("user-registration/api/v1/registration/patient")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("message", equalTo("User " + firstName + " " + lastName + " registration process is in progress."))
                .extract().response().asString();

        System.out.println(response);

        //----------------------------------

        //Successful Registration - only mandatory fields
        String responseMandatory = given().log().all()
                .header("Content-Type", "application/json")
                .body(RegistrationBody.mandatoryRegistrationBody(firstName, lastName))
                .when().post("user-registration/api/v1/registration/patient")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("message", equalTo("User " + firstName + " " + lastName + " registration process is in progress."))
                .extract().response().asString();

        System.out.println(responseMandatory);

        //----------------------------------
    }
}
