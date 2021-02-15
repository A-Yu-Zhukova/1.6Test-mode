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

        public static RegistrationDto generateUserBlocked() {
            return new RegistrationDto(
                    "user2",
                    "password2",
                    "blocked"
            );
        }

        public static String getBadLogin() {
            return "user3";
        }

        public static String getBadPassword() {
            return "password3";
        }
    }
}