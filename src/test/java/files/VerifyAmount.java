package files;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyAmount {

    @Test
    public void sumOfCourses() {

        JsonPath js = new JsonPath(payload.CoursePrice());

        int count = js.getInt("courses.size()");
        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for(
        int i = 0;
        i<count;i++)

        {
            int coursePrice = js.get("courses[" + i + "].price");
            int copiesSold = js.getInt("courses[" + i + "].copies");
            int coursePaid = coursePrice * copiesSold;
            sum = sum + coursePaid;
        }

        int bundlePrice = js.getInt("dashboard.purchaseAmount");
        System.out.println(bundlePrice);
        System.out.println(sum);
        Assert.assertEquals(bundlePrice,sum);


    }
}
