package apiWorks;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

import java.util.List;
import java.util.Map;


public class withBaseURI {

    @BeforeClass
    public void beforeClass() {

        baseURI = "http://api.cybertektraining.com";


    }

    @Test
    public void test6() {
       /* given().accept(ContentType.JSON).and().pathParam("id",21563)
                .when().get("student/{id}")
                .then().assertThat().contentType("application/json;charset=UTF-8")
                .and().statusCode(200);*/

        Response response = given().accept(ContentType.JSON)
                .when().get("student/all");
        System.out.println("response.path(\"students.studentId[0]\") = " + response.path("students.studentId[0]"));
        System.out.println("response.path(\"students.contact[1].emailAddress\") = " + response.path("students.contact[1].emailAddress"));

        JsonPath jsonPath = response.jsonPath();

        List<String> firstNames = jsonPath.getList("students.fi ndAll{it.gender=='F'}.firstName");
        List<Integer> batchNumberFirstName = jsonPath.getList("students.findAll{it.batch==15}.firstName");
        List<Integer> studentId = jsonPath.getList("students.contact.findAll {it.contactId==21583}.studentId");
        List<String> firstName = jsonPath.getList("students.findAll{it.company}.findAll{it.address}.findAll{it.city==\"WA CITY\"}.firstName");


        System.out.println("firstNames = " + firstNames);
        System.out.println("studentId = " + studentId);
        System.out.println("firstName = " + firstName);
        System.out.println("batchNumberFirstName = " + batchNumberFirstName);

    }


    @Test
    public void test7() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 21601)
                .when().get("student/{id}");

        assertEquals(response.statusCode(), 200);

        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);

        String phoneNumber = jsonPath.getString("students.contact[0].phone");
        System.out.println("phoneNumber = " + phoneNumber);

        String cityName = jsonPath.getString("students.company[0].address.city");
        int zipCode = jsonPath.getInt("students.company[0].address.zipCode");

        System.out.println("cityName = " + cityName);
        System.out.println("zipCode = " + zipCode);

    }


    @Test
    public void testTeacher() {

        given().accept(ContentType.JSON).and().pathParam("id", 10423)
                .when().log().all()

                .get("/teacher/{id}")

                .then().statusCode(200)
                .assertThat().contentType("application/json;charset=UTF-8")
                .assertThat().header("Content-Length", "236")
                .assertThat().header("Date", notNullValue())
                .assertThat().body("teachers.firstName[0]", equalTo("Alexander"),
                "teachers.phone[0]", equalTo("12345689"))
                .log().all();


    }


    @Test
    public void testTeacherDepartment() {

        given().accept(ContentType.JSON).and().pathParam("name", "Computer")
                .when().get("/teacher/department/{name}")
                .then().assertThat().statusCode(200)
                .assertThat().contentType(equalTo("application/json;charset=UTF-8"))
                .body("teachers.firstName", hasItems("Alexander", "Marteen"));

    }

    @Test
    public void getAllTeachers() {

        Response response = given().accept(ContentType.JSON).when().get("teacher/all");
        Assert.assertEquals(response.statusCode(), 200);


       //Map<String,Map<String, Object>> allTeacher = response.body().as(Map.class);
        Map<String,List<Map<String, Object>>> allTeacher = response.body().as(Map.class);
        Object firstName=allTeacher.get("teachers").get(0).get("firstName");
        System.out.println("allTeacher.get(\"teachers\").get(0).get(\"firstName\") = " + allTeacher.get("teachers").get(0).get("firstName"));
        System.out.println("firstName = " + firstName);


        //System.out.println("allTeacher = " + allTeacher);

       /* Object firstTeacherName=allTeacher.get("teachers").get("firstName")
        //System.out.println("allTeacher.get(\"teachers\").get(\"firstName\") = " + allTeacher.get("teachers").get("firstName"));

        System.out.println("firstTeacherName = " + firstTeacherName);*/


    }


}


