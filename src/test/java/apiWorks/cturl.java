package apiWorks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class cturl {
    String baseCturl = "http://api.cybertektraining.com";
    String url = null;

    @Test
    public void test1() {

        url = baseCturl + "/student/all";
        System.out.println("url = " + url);
        Response response = RestAssured.get(url);
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println(response.prettyPrint());

    }


    @Test
    public void test2() {

        url = baseCturl + "/student/21563";


        Response response = RestAssured.given().accept(ContentType.JSON).get(url);
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println(response.prettyPrint());


        Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertEquals(response.contentType(), "application/json;charset=UTF-8");


    }

    @Test
    public void test3() {
        url = baseCturl + "/student/21563";
        RestAssured.given().accept(ContentType.JSON)
                .when().get(url).then()
                .assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8");


    }

    @Test
    public void test4() {
        url = baseCturl + "/student/21563";
        given().accept(ContentType.JSON)
                .when().get(url)
                .then().assertThat().contentType("application/json;charset=UTF-8")
                .and().statusCode(200);
        Response response = given().accept(ContentType.JSON).get(url);
        Assert.assertTrue(response.body().asString().contains("BOND"), "BOND doesn't contain");


    }

    @Test
    public void test5() {
        url = baseCturl + "/student/all";
        given().accept(ContentType.JSON)
                .when().get(url).then()
                .assertThat().statusCode(200)
                .and().contentType("application/json;charset=UTF-8");
        Response response = given().accept(ContentType.JSON).get(url);
        Assert.assertTrue(response.body().asString().contains("Vera"),"vera  is not");
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"),"Date is not exist");

    }




}
