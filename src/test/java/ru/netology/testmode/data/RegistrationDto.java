package ru.netology.testmode.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationDto {
    private final String name;
    private final String password;
    private final String active;
}
