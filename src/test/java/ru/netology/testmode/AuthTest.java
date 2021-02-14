package ru.netology.testmode;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.data.DataGenerator;

import static io.restassured.RestAssured.given;

public class AuthTest {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://127.0.0.1")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeAll
    static void setUpAll() {
        // сам запрос
        given() // "дано"
                .spec(requestSpec)
                .body(DataGenerator.Registration.generateUser())
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void testCorrect() {
    }
}