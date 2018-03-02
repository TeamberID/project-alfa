package ru.kpfu.itis.app.forms;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {
    private String login;
    private String password;
    private String key;
}