package ru.kpfu.itis.app.services;

import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.model.User;


public interface AuthenticationService {
    User getUserByAuthentication(Authentication authentication);
}