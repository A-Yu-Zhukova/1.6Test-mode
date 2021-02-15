package ru.netology.testmode.test;

import com.codeborne.selenide.SelenideElement;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.testmode.api.ApiHelper;
import ru.netology.testmode.data.DataGenerator;
import ru.netology.testmode.data.RegistrationDto;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class AuthTest {
    private static RegistrationDto user;
    private static RegistrationDto userBlocked;
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://127.0.0.1")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static ApiHelper apiHelper = new ApiHelper(requestSpec);

    @BeforeAll
    static void setUpAll() {
        user = DataGenerator.Registration.generateUser();
        userBlocked = DataGenerator.Registration.generateUserBlocked();

        apiHelper.SendData(user);
        apiHelper.SendData(userBlocked);

    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void testCorrect() {
        SelenideElement form = $("form");
        form.$("[data-test-id=login] input").setValue(user.getLogin());
        form.$("[data-test-id=password] input").setValue(user.getPassword());
        form.$("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldNotBe(visible, Duration.ofSeconds(15));
        //$("[data-test-id=error-notification] .notification__content").shouldHave(exactText(expected));
    }

    @Test
    void testBadLogin() {
        SelenideElement form = $("form");
        form.$("[data-test-id=login] input").setValue(DataGenerator.Registration.getBadLogin());
        form.$("[data-test-id=password] input").setValue(user.getPassword());
        form.$("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void testBadPassword() {
        SelenideElement form = $("form");
        form.$("[data-test-id=login] input").setValue(user.getLogin());
        form.$("[data-test-id=password] input").setValue(DataGenerator.Registration.getBadPassword());
        form.$("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void testBlockedUser() {
        SelenideElement form = $("form");
        form.$("[data-test-id=login] input").setValue(userBlocked.getLogin());
        form.$("[data-test-id=password] input").setValue(userBlocked.getPassword());
        form.$("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible, Duration.ofSeconds(15));
    }
}