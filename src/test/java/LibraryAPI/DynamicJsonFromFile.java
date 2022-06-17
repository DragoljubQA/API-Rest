package LibraryAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class DynamicJsonFromFile {

    //content of the file ti String -> content of file can convert into Byte -> Byte data to String

    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Dragoljub\\Desktop\\DataFile2.json"))))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethodsLibrary.rawToJson(response);
        String ID = js.get("ID");
        System.out.println("ID je " + ID);

    }
}
