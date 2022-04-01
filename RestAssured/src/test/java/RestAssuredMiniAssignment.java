import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import java.io.File;

public class RestAssuredMiniAssignment {

    @Test
    public void test_get_call(){

        RestAssured.useRelaxedHTTPSValidation();
        Response response=given().
                header("content-type","application/json").
                when().
                get("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(200).log().status().log().headers().extract().response();
        assertThat(response.path("[39].userId"), is(equalTo(4)));
        JSONArray arr = new JSONArray(response.asString());
        int flag = 1;
        for(int i=0;i<arr.length();i++){

            Object obj = arr.getJSONObject(i).get("title");
            if( !(obj instanceof String) ) {
                flag = 0;
                break;
            }
        }
        assertThat(flag,is(equalTo(1)));


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
