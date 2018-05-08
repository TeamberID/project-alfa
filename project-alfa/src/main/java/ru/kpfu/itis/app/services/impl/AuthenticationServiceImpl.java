package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.UserDataRepository;
import ru.kpfu.itis.app.security.details.UserDetailsImpl;
import ru.kpfu.itis.app.security.role.Role;
import ru.kpfu.itis.app.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserDataRepository usersRepository;

    @Override
    public UserData getUserByAuthentication(Authentication authentication) {
        UserDetailsImpl currentUserDetails = (UserDetailsImpl)authentication.getPrincipal();
        UserData currentUserModel = currentUserDetails.getUser();
        Long currentUserId = currentUserModel.getId();
        return usersRepository.findOne(currentUserId);
    }

    @Override
    public String defineDefaultURL(Authentication authentication) {
        UserData userData = getUserByAuthentication(authentication);
        if (userData.getRole().equals(Role.ADMIN)) {
            return "/admin";
        }
        return "/user/profile";
    }
}