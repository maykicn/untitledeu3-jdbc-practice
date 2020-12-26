package apitests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithParameters {
    @BeforeClass
    public void beforeClass(){

        baseURI="http://3.80.189.73:8000";
    }

      /*
          Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json;charset=UTF-8
          And "Blythe" should be in response payload
       */

    @Test
    public void getSpartanID_Positive_PathParam(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",5)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),200);
        assertEquals(response.getContentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Blythe"));



    }

     /*
          Given accept type is Json
          And Id parameter value is 500
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 404
          And response content-type: application/json;charset=UTF-8
          And Spartan Not Found message should be in response payload
       */

    @Test
    public void getSpartanID_Negative_PathParam(){

        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",800)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Spartan Not Found"));

 }

 /*
          Given accept type is Json
          And query parameter values are:
          gender|female
          nameContains|e
          When user sends GET request to /api/spartans/search
          Then response status code should be 200
          And response content-type: application/json;charset=UTF-8
          And "Female" should be in response payload
          And "Janette" should be in response payload
       */


    @Test
    public void getSpartanID_Negative_PathParam2(){

        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",800)
                .when().get("/api/spartans/{id}");
        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Spartan Not Found"));

    }

    @Test
    public void positiveTestWithQueryParam(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "female")
                .and().queryParam("nameContains", "e")
                .when().get("api/spartans/search");

        assertEquals(response.getStatusCode(),200 );
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.getBody().asString().contains("Female"));
        assertTrue(response.getBody().asString().contains("Janette"));

    }

    @Test
    public void positiveTestWithQueryParamWithmaps(){

        //create a map and add query parameters

        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response=given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("api/spartans/search");
        assertEquals(response.getStatusCode(),200 );
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.getBody().asString().contains("Female"));
        assertTrue(response.getBody().asString().contains("Janette"));








    }













}
