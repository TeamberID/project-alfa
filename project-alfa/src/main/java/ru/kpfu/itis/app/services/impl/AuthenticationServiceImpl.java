package ru.kpfu.itis.app.services.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.Token;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.TokenRepository;
import ru.kpfu.itis.app.repositories.UserDataRepository;
import ru.kpfu.itis.app.security.details.UserDetailsImpl;
import ru.kpfu.itis.app.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserDataRepository usersRepository;

    private TokenRepository tokenRepository;

    public AuthenticationServiceImpl(UserDataRepository usersRepository, TokenRepository tokenRepository) {
        this.usersRepository = usersRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public UserData getUserByAuthentication(Authentication authentication) {
        UserDetailsImpl currentUserDetails = (UserDetailsImpl)authentication.getPrincipal();
        UserData currentUserModel = currentUserDetails.getUser();
        Long currentUserId = currentUserModel.getId();
        return usersRepository.findOne(currentUserId);
    }

    @Override
    public String getAuthToken(Authentication authentication) {
        Token token = Token.builder()
                .userData(getUserByAuthentication(authentication))
                .value(RandomStringUtils.random(10, true, true))
                .build();
        tokenRepository.save(token);
        return token.getValue();
    }
}