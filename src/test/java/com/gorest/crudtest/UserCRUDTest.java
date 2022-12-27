package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    @Test
    public void createRecord() {

        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName("Savitri");
        postsPojo.setEmail("prime12345@gmail.com");
        postsPojo.setGender("female");
        postsPojo.setStatus("active");

        Response response = given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .body(postsPojo)
                .post("/users");
        response.prettyPrint();
        response.then().log().all().statusCode(201);
    }

    @Test
    public void updateRecord() {

        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName("Savitri");
        postsPojo.setEmail("prime1231452@gmail.com");
        postsPojo.setGender("female");
        postsPojo.setStatus("active");

        Response response = given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .body(postsPojo)
                .patch("/users/10614");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void deleteRecord() {

        Response response = given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .delete("/users/10614");
        response.prettyPrint();
        response.then().statusCode(204);
    }

}
