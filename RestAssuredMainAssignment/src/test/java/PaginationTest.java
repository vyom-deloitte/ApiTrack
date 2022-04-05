import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ExtentReport.class)
public class PaginationTest extends  Base{
    @Test(priority = 5)
    public void paginationFor2(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request2 = RestAssured.given();
        request2.header("Authorization","Bearer "+ tokenGenerated)
                .header("Content-Type","application/json");
        Response response2 = request2.get("/task?limit=2");
        response2.prettyPrint();
        log.info("Paginated for 2  Successfully");
    }
    @Test(priority = 6)
    public void paginationFor5(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request5 = RestAssured.given();
        request5.header("Authorization","Bearer "+ tokenGenerated)
                .header("Content-Type","application/json");
        Response response5 = request5.get("/task?limit=5");
        response5.prettyPrint();
        log.info("Paginated for 5  Successfully");
    }
    @Test(priority = 7)
    public void paginationFor10(){
        RestAssured.baseURI = "https://api-nodejs-todolist.herokuapp.com";
        RequestSpecification request10 = RestAssured.given();
        request10.header("Authorization","Bearer "+ tokenGenerated)
                .header("Content-Type","application/json");
        Response response10 = request10.get("/task?limit=10");
        response10.prettyPrint();
        log.info("Paginated for 5 values Successfully");
    }
}
