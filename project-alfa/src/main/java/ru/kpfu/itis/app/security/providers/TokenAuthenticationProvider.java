package ru.kpfu.itis.app.security.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.app.model.Token;
import ru.kpfu.itis.app.repositories.TokenRepository;
import ru.kpfu.itis.app.security.auth.TokenAuthentication;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component("tokenAuthenticationProvider")
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private TokenRepository tokenRepository;

    public TokenAuthenticationProvider(UserDetailsService userDetailsService, TokenRepository tokenRepository) {
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        Token token = tokenRepository.findByValue(tokenAuthentication.getName()).orElseThrow(
                () -> new BadCredentialsException("Invalid Authentication Token. Access denied.")
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUserData().getLogin());
        tokenAuthentication.setUserDetails(userDetails);
        tokenAuthentication.setAuthenticated(true);
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return authenticationClass.equals(TokenAuthentication.class);
    }
}
