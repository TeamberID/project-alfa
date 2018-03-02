package ru.kpfu.itis.app.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.UserDatasRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDatasRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UserDatasRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserData user = usersRepository.findOneByLogin(login).orElseThrow(()
                -> new IllegalArgumentException("UserData not found by login <" + login + ">"));
        return new UserDetailsImpl(user);
    }
}