package ru.netology.testmode.data;

import ru.netology.testmode.api.ApiHelper;
import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private static Faker faker = new Faker(new Locale("en"));

        private Registration() {
        }

        public static RegistrationDto generateUser(boolean isBlocked) {

            RegistrationDto user = new RegistrationDto(
                    faker.name().username(),
                    faker.internet().password(),
                    isBlocked ? "blocked" : "active"
            );
            ApiHelper.sendData(user);
            return user;
        }


        public static String getBadLogin() {
            return faker.name().username();
        }

        public static String getBadPassword() {
            return faker.internet().password();
        }
    }
}