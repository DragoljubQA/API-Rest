import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Basics2 {
    public static void main(String[] args) {

        //Given - all input details
        //When - Submit the API
        //Then - Validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();

        System.out.println("RESPONSE: " + response);

        JsonPath js = new JsonPath(response); //for parsing JSON
        String placeID = js.getString("place_id");

        System.out.println("Place ID: " + placeID);

        //Update Place

        String newAddress = "Kralja Bodina 15";

        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        " \"place_id\":\""+placeID+"\",\n" +
                        " \"address\":\""+newAddress+"\",\n" +
                        " \"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //Get Place

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);

        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);

    }
}
