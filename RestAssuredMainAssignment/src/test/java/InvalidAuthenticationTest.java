import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
@Listeners(ExtentReport.class)
public class InvalidAuthenticationTest extends  Base{
    @Test(priority = 8)
    public void InvalidAuthentication() throws IOException { //registering again from same username and password(duplicate user)
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request = RestAssured.given();
        String payload = "{\n" +
                "  \"name\" : \""+username+"\",\n" +
                "  \"email\" : \""+email+"\",\n" +
                "  \"password\" : \""+password+"\"\n" +
                "}";
        request.header("Content-Type", "application/json");
        try {
            Response responsefromGeneratedToken = request.body(payload).post("/user/register");
            responsefromGeneratedToken.prettyPrint();
            String jsonString = responsefromGeneratedToken.getBody().asString();
            tokenGenerated = JsonPath.from(jsonString).get("token");
            request.header("Authorization", "Bearer" + tokenGenerated)
                    .header("Content-Type", "application/json");
            String loginDetails = "{\n" +
                    "  \"email\" : \"" + email + "\",\n" +
                    "  \"password\" : \"" + password + "\"\n" +
                    "}";
            try {
                Response responseLogin = request.body(loginDetails).post("/user/login");
            } catch (Exception e) {
                System.out.println(e);
            }
        }catch (Exception e){
            System.out.println(e.getMessage()+" Duplicate User Detected");
        }
    }
}
