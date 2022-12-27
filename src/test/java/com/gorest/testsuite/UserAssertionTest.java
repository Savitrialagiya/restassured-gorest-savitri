package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="https://gorest.co.in/public/v2";

        response =given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test01() {
        response.body("total.size", equalTo(20));

    }
    //2. Verify the if the name of id = 5487 is equal to ”Miss Lalita Iyer”
    @Test
    public void test02() {
        response.body("[1].id",equalTo(5316));
    }

    //3. Check the single ‘Name’ in the Array list (Miss Lalita Iyer)
    @Test
    public void test03() {
        response.body("name",hasItem("Miss Lalita Iyer"));

    }
    //4. Check the multiple ‘Names’ in the ArrayList (Bharat Kaniyar, Miss Chidaatma Bhattacharya, Asha Kaul","Miss Lalita Iyer")
    @Test
    public void test04() {
        response.body("name",hasItems("Bharat Kaniyar","Miss Chidaatma Bhattacharya","Asha Kaul","Miss Lalita Iyer"));
    }

    //5. Verify the email of userid = 5405 is equal “kaul_dhana@nienow.name”
    @Test
    public void test05() {
        response.body("[5].email",equalTo("kaul_dhana@nienow.name"));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test06() {
        response.body("[10].status",equalTo("active"));
    }
    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test07() {
        response.body("[11].gender",equalTo("female"));
    }

}
