package ru.netology.testmode.api;

import io.restassured.specification.RequestSpecification;
import ru.netology.testmode.data.DataGenerator;
import ru.netology.testmode.data.RegistrationDto;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    RequestSpecification requestSpec;

    public ApiHelper(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public void SendData(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
