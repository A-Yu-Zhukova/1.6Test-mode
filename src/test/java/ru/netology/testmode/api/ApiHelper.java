package ru.netology.testmode.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.testmode.data.DataGenerator;
import ru.netology.testmode.data.RegistrationDto;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://127.0.0.1")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void sendData(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
