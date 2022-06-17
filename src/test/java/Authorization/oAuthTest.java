package Authorization;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.restassured.RestAssured.given;


public class oAuthTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:51144");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=randomString");
        //driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div/div/ul/li[1]/div")).click();
        Thread.sleep(2000);
        String URL = driver.getCurrentUrl();
        String partialCode = URL.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println("code je "+code);

        //Kad bi radili grant type Client credentials krenuli bi odavde na dole

        String accessTokenRequest =
        given()
                .urlEncodingEnabled(false)
                .queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("grant_type", "authorization_code")
                .queryParams("state", "verifyfjdss")
                .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        
        JsonPath js = new JsonPath(accessTokenRequest);
        String accessToken = js.getString("access_token");

        String response =
        given().contentType("application/json").
                queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();

        System.out.println(response);

    }
}
