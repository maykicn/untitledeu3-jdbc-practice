package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeClass(){

        baseURI= ConfigurationReader.get("cbt_api_url");
    }



    @Test
    public void test(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",15642)
                .when().get("/student/{id}");

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        assertEquals(response.statusCode(),200);

        //assign response to jsonpath
        JsonPath jsonPath=response.jsonPath();
        String firstName=jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);


        String lastName=jsonPath.getString("students.lastName[0]");
        System.out.println("lastName = " + lastName);
        String phone=jsonPath.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);


        //get me city and zipcode ,do assertion
        int zipCode=jsonPath.getInt("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);
        assertEquals(zipCode,60606);




















    }



}
