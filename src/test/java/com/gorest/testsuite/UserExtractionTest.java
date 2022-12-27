package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Object> ids = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total records are : " + ids.size());
        System.out.println("------------------End of Test---------------------------");
    }

    // 2. Extract the all Names
    @Test
    public void test002() {
        List<List<String>> allNames = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All names are : " + allNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<List<String>> name = response.extract().path("findAll{it.status=='inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status = inactive are : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<List<String>> gender = response.extract().path("findAll{it.status=='active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all the object whose status = active are : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<?> name = response.extract().path("findAll{it.gender=='female'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female are : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<List<String>> emails = response.extract().path("findAll{it.status=='inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The emails of the object where status = inactive are : " + emails);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<List<String>> ids = response.extract().path("findAll{it.gender=='male'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of the object where gender = male are : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<Object> status = response.extract().path("status");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the status are : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test010() {
        List<?> email = response.extract().path("findAll{it.name=='Aashritha Bhattathiri'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email of the object where name = Aashritha Bhattathiri is : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5471
    @Test
    public void test011() {
        List<?> gender = response.extract().path("findAll{it.id==5305}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("gender of id = 5305 is : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }

}
