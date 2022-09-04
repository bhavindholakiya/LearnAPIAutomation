package main.java.org.testcase;

import io.restassured.path.json.JsonPath;
import main.java.org.requestBody.Helper;
import main.java.org.requestBody.Payload;
import org.testng.Assert;

public class ComplexJson {

    public static void main(String[] args) {
        /*
         * Below Testcases will be covered in this Class File.
         * 1. Print No of courses returned by API
         * 2. Print Purchase Amount
         * 3. Print Title of the first course
         * 4. Print All course titles and their respective Prices
         * 5. Print no of copies sold by RPA Course
         * 6. Verify if Sum of all Course prices matches with Purchase Amount
         * */
        JsonPath js = Helper.RawToJson(Payload.CoursePrice());

        // 1. Print No of courses returned by API
        int NoOfCourse = js.getInt("courses.size()");
        System.out.println("No. of Courses are: "+NoOfCourse);

        // 2. Print Purchase Amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount is: "+purchaseAmount);

        //3. Print Title of the first course
        String FirstTitle = js.getString("courses[0].title");
        System.out.println("The First Course Title is: "+FirstTitle);

        //4. Print All course titles and their respective Prices
        System.out.println("********** All course titles and their respective Prices **********");
        for(int i=0; i<NoOfCourse; i++){
            String CourseName = js.getString("courses["+i+"].title");
            int CoursePrice = js.getInt("courses["+i+"].price");
            System.out.println(i+1+". "+CourseName+ ": "+CoursePrice);
        }
        System.out.println("********************************************************************");

        // 5. Print no of copies sold by RPA Course
        for(int i=0;i<NoOfCourse;i++){
            String courseTitle = js.getString("courses["+i+"].title");
            if (courseTitle.equalsIgnoreCase("RPA")){
                int soldCopiesOfRPA = js.getInt("courses["+i+"].copies");
                System.out.println("No of copies sold by RPA Course: "+soldCopiesOfRPA);
                break;
            }
        }
        System.out.println("******************************************************************* *");

        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int ActualTotal = 0;
        for(int j=0; j<NoOfCourse; j++){
            int CoursePrice = js.getInt("courses["+j+"].price");
            int SoldCopies = js.getInt("courses["+j+"].copies");
            int TotalPerCourse = CoursePrice * SoldCopies;
            ActualTotal +=  TotalPerCourse;
        }

        System.out.println("Expected Purchase amount is: "+purchaseAmount);
        System.out.println("Actual Purchase amount is: "+ActualTotal);
        Assert.assertEquals(ActualTotal, purchaseAmount);
    }
}
