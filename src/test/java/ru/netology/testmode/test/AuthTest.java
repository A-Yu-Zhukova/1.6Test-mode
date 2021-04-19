package ru.netology.testmode.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.data.DataGenerator;
import ru.netology.testmode.data.RegistrationDto;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    private static RegistrationDto user;
    private static RegistrationDto userBlocked;

    @BeforeAll
    static void setUpAll() {
        user = DataGenerator.Registration.generateUser(false);
        userBlocked = DataGenerator.Registration.generateUser(true);
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
        $(".heading").shouldHave(text("Личный кабинет"));
    }

    @Test
    void testBadLogin() {
        SelenideElement form = $("form");
        form.$("[data-test-id=login] input").setValue(DataGenerator.Registration.getBadLogin());
        form.$("[data-test-id=password] input").setValue(user.getPassword());
        form.$("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void testBadPassword() {
        SelenideElement form = $("form");
        form.$("[data-test-id=login] input").setValue(user.getLogin());
        form.$("[data-test-id=password] input").setValue(DataGenerator.Registration.getBadPassword());
        form.$("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void testBlockedUser() {
        String expected = "Ошибка! Пользователь заблокирован";
        SelenideElement form = $("form");
        form.$("[data-test-id=login] input").setValue(userBlocked.getLogin());
        form.$("[data-test-id=password] input").setValue(userBlocked.getPassword());
        form.$("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(text("Пользователь заблокирован"));
    }
}