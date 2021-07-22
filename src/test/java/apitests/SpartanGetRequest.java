package apitests;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class SpartanGetRequest {

    String spartanUrl="http://52.86.179.144:8000";

    @Test
    public void test1(){

        Response response=when().get(spartanUrl+"/api/spartans/89");
        System.out.println(response.statusCode());
        System.out.println(response.prettyPrint());


    }


     /* TASK
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json;charset=UFT-8
        and json body should contain Fidole
     */

    @Test
    public void test2(){

        Response response=when().get(spartanUrl+"/api/spartans/3");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
        Assert.assertTrue(response.getBody().asString().contains("Fidole"));



    }

      /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
        */

    @Test
    public void test3(){

        Response response=when().get(spartanUrl+"/api/hello");
        //verify teh satattuse code
        Assert.assertEquals(response.statusCode(),200);
        //verify content*-type
        Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");
        //verify we have headersname date
        Assert.assertTrue(response.headers().hasHeaderWithName("Content-Length"));
        Assert.assertTrue(response.headers().hasHeaderWithName("date"));

        System.out.println("response.headers().hasHeaderWithName(\"date\") = " + response.headers().hasHeaderWithName("date"));
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"date\") = " + response.header("date"));

        //verify content length is 17
        Assert.assertEquals(response.header("Content-Length"),"17");

        //verify body has hello from sparta

        System.out.println("response.body().asString() = " + response.body().asString());
        Assert.assertEquals(response.body().asString(),"Hello from Sparta");
        // ya da
        Assert.assertTrue(response.body().asString().contains("Hello from Sparta"));

    }








}
