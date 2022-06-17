import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParse {
    public static void main(String[] args) {

        JsonPath js = new JsonPath(payload.CoursePrice());

        /*
        1. Print No of courses returned by API
        2. Print Purchase Amount
        3. Print Title of the first course
        4. Print All course titles and their respective Prices
        5. Print no of copies sold by RPA Course
        6. Verify if Sum of all Course prices matches with Purchase Amount
        */

        //1. Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        System.out.println("************************");

        //2. Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        System.out.println("************************");

        //3. Print Title of the first course
        String firstTitle = js.getString("courses[0].title");
        System.out.println(firstTitle);

        System.out.println("************************");

        //4. Print All course titles and their respective Prices
        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses["+i+"].title");
            int coursePrice = js.get("courses["+i+"].price");
            //String coursePrice2 = js.get("courses["+i+"].price").toString();

            System.out.println(courseTitles);
            System.out.println(coursePrice);
        }

        System.out.println("************************");

        //5. Print no of copies sold by RPA Course
        for (int i = 0; i < count; i++) {
            String courseTitles = js.get("courses["+i+"].title");
            if (courseTitles.equalsIgnoreCase("RPA")) {
                int copiesSold = js.getInt("courses["+i+"].copies");
                System.out.println(copiesSold);
                break;
            }
        }

        System.out.println("************************");

        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for (int i = 0; i < count; i++) {
            int coursePrice = js.get("courses["+i+"].price");
            int copiesSold = js.getInt("courses["+i+"].copies");
            int coursePaid = coursePrice * copiesSold;
            sum = sum + coursePaid;
        }
        int bundlePrice = js.getInt("dashboard.purchaseAmount");
        System.out.println(bundlePrice);
        System.out.println(sum);
        Assert.assertEquals(bundlePrice, sum);

    }
}
