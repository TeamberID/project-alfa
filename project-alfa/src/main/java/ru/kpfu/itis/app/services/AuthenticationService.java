package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.model.UserData;


public interface AuthenticationService {
    UserData getUserByAuthentication(Authentication authentication);
}