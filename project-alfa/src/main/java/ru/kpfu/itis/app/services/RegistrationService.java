package ru.kpfu.itis.app.services;


import ru.kpfu.itis.app.forms.UserRegistrationForm;

public interface RegistrationService {
    void register(UserRegistrationForm userForm);
}