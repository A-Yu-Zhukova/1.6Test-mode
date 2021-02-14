package ru.netology.testmode.data;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto generateUser() {
            return new RegistrationDto(
                    "user",
                    "password",
                    "active"
            );
        }
    }
}