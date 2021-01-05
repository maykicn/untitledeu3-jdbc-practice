package Day8;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;


public class PUTRequestDemo {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){
        //create one map for the put  resquest json body
        Map<String,Object> putRequestMap=new HashMap<>();
        putRequestMap.put("name","Put äName");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone","1111111112");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",89)
                .body(putRequestMap).
        when()
                .put("/api/spartans/{id}")
        .then().log().all()
                .assertThat().statusCode(204);

        //send get request to verify body

        Response response = given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id", 89)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.path("name"),"Put äName");
        System.out.println(response.path("name").toString());


    }

    @Test
    public void PatchTest(){

        //create one map for the put  resquest json body
        Map<String,Object> patchtRequestMap=new HashMap<>();
        patchtRequestMap.put("name","TJ");


        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .pathParam("id",89)
                .body(patchtRequestMap)
        .when()
                .patch("/api/spartans/{id}")
        .then().log().all()
                .assertThat().statusCode(204);



    }





}
