import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ExtentReport.class)
public class UserValidationTest extends Base {
    @Test(priority = 3)
    public void ValidateUser(){//logging in already registered user having valid token
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com/user/me";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer "+ tokenGenerated)
                .header("Content-Type","application/json");
        Response responsevalidateUser = request.get();
        responsevalidateUser.prettyPrint();
        log.info("User Validated Successfully");
    }
}
