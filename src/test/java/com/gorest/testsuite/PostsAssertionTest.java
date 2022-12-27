package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //    1. Verify if the total record is 25
    @Test
    public void test01() {
        response.body("total.size", equalTo(25));

    }

    //    2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.
    @Test
    public void test2() {
        response.body("[2].title", equalTo("Ad ipsa coruscus ipsam eos demitto centum."));
    }

    //    3. Check the single user_id in the Array list (5522)
    @Test
    public void test3() {
        response.body("user_id", hasItem(5522));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test04() {
        response.body("id", hasItems(2693, 2611, 2612));
    }

    //    5. Verify the body of userid = 2693 is equal “Carus eaque voluptatem. Calcar
//    spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
//    Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
//    Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
//    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
//    cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
//    adflicto. Assentator umquam pel."”
    @Test
    public void test05() {
        response.body("[5].body", equalTo("Vulnero caput capio. Titulus esse coruscus. Adsum torrens perferendis. Pecunia beatus uberrime. Ut despecto vociferor. Abeo et vaco. Creber sursum creptio. Cattus abduco quo. Usque et adipiscor. Suffoco defetiscor spiritus. Validus vomer vitiosus. Infit vado causa. Dedecor degenero crustulum. Tabgo xiphias vicissitudo. Adstringo adsuesco vir. Defaeco velit eum. Creber comes solum. Soleo animi cognatus. Textilis solio creta. Vereor textus cicuta. Commemoro amicitia cibo."));
    }
}
