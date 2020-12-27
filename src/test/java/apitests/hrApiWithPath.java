package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class hrApiWithPath {


    @BeforeClass
    public void beforeClass(){

        baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":\"2\"}")
                .when().get("/countries");
        assertEquals(response.statusCode(),200);

        //print limit value

        int firstLimit = response.path("limit");
        System.out.println("firstLimit = " + firstLimit);

        System.out.println("response.path(\"hasMore\").toString() = " + response.path("hasMore").toString());

        System.out.println("response.path(\"items:region_id\").toString() = " + response.path("items.region_id[0]"));

        String firstCountryId=response.path("items.country_id[0]");
        System.out.println("firstCountryId = " + firstCountryId);

        String secondCountryId=response.path("items.country_id[1]");
        System.out.println("secondCountryId = " + secondCountryId);
        String secondCountryName=response.path("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        String link2=response.path("items.links[2].href[0]");
        System.out.println("link2 = " + link2);

        //get all countries
        List<String> countryNames=response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert taht all regions'ids are equal to 2

        List<Integer>regionsIds=response.path("items.region_id");
        int a=regionsIds.get(2);

        for (int regionsId : regionsIds) {
            System.out.println("regionsId = " + regionsId);

            assertEquals(regionsId,2);

        }

        /*for (int i = 0; i < regionsIds.size(); i++) {

            assertEquals(regionsIds.get(i),2);

        }*/
 }

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have  only IT_PROG  as a job id

        List <String> jobIDs=response.path("items.job_id");
        for (String jobID : jobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals(jobID,"IT_PROG");


        }















        }






}
