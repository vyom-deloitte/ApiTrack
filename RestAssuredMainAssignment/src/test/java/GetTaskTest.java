import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ExtentReport.class)
public class GetTaskTest extends Base {
    @Test(priority = 4)
    public void getTask(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com/task";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer "+ tokenGenerated)
                .header("Content-Type","application/json");
        Response responsegetTask = request.get();
        responsegetTask.prettyPrint();
        log.info("Tasks Fetched Successfully");
    }
}
