package LibraryAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJsonDataProvider {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(dPayloadLibraryFromTest.addBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethodsLibrary.rawToJson(response);
        String ID = js.get("ID");
        System.out.println("ID je " + ID);

        //Test ce ici sam onoliko puta koliko objekata kreiramo u getData
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        //array - collection of elements
        //multidimensional array - collection of arrays - [] []

        return new Object[][] {{"iojqg", "185"}, {"kjng", "163"}, {"pjts", "812"}};

    }

}
