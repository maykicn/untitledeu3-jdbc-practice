package apitests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithJsonPath {

    @BeforeClass
    public void beforeClass(){

        baseURI= ConfigurationReader.get("hr_api_url");
    }


    @Test
    public void test11(){

        Response response = get("/countries");

        //assign to jsonpath

        JsonPath jsonPath=response.jsonPath();
        String secondCountryName=jsonPath.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all couty ids

        List<String> countryID=jsonPath.getList("items.country_id");
        System.out.println("countrID = " + countryID);
        
        
        //get all countr names ehere their region id is equal to  2
        List<String>countryNameWithRegionId2=jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countryNameWithRegionId2 = " + countryNameWithRegionId2);
}

    @Test
    public void test2(){

        Response response=given().queryParam("limit",107)
                .when().get("/employees");

        JsonPath jsonPath=response.jsonPath();

        //get me all firstnmae if employees who is mworkin as IT_PROG
        List<String>firstName=jsonPath.getList("items.first_name");
        System.out.println("firstName = " + firstName);

        List<String>FirstNameWithWorkingAsIT_PROG=jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.first_name");
        System.out.println("FirstNameWithWorkingAsIT_PROG = " + FirstNameWithWorkingAsIT_PROG);

        //get me all firstname of employees who is making more than 100000

        List<String> firstNameWithHighe10000=jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("firstNameWithHighe10000 = " + firstNameWithHighe10000);

        //get me first name of who is making highest salary

        String kingName=jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingName = " + kingName);



    }






}
