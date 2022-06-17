package Demo;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        addPlace place = new addPlace();
        place.setAccuracy(50);
        place.setAddress("29, side layout, cohen 09");
        place.setLanguage("English");
        place.setName("Frontline house");
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("https://rahulshettyacademy.com");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        place.setTypes(myList);
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        place.setLocation(location);

        String response =
        given().log().all()
                .queryParam("key", "qaclick123")
                .body(place)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(response);


    }
}
