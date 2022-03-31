import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import java.io.File;

public class RestAssuredMiniAssignment {

    @Test
    public void test_get_call(){

        given().
                baseUri("https://jsonplaceholder.typicode.com/posts").header("Content-Type","application/json").
                when().
                get("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(200).body("userId[39]",equalTo(4)).body("title[39]",equalTo("enim quo cumque"));


    }

    @Test
    public void test_put_call(){
        File JsonData=new File("C:\\HUAPI\\RestAssured\\src\\test\\resources\\putdata.json");
               given().
                    baseUri("https://reqres.in/api/users").
                    body(JsonData).
                    header("Content-Type","application/json").
                when().
                       put("/users").
                then().
                       statusCode(200).body("name",equalTo("Arun")).body("job",equalTo("Manager"));




    }

}
