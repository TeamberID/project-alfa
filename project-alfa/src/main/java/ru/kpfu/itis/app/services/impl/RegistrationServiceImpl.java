package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.UserRegistrationForm;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.repositories.UsersRepository;
import ru.kpfu.itis.app.security.role.Role;
import ru.kpfu.itis.app.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(UserRegistrationForm userForm) {
        User newUser = User.builder()
                .login(userForm.getLogin())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .university(userForm.getUniversity())
                .institute(userForm.getInstitute())
                .course(userForm.getCourse())
                .role(Role.USER)
                .build();
        usersRepository.save(newUser);
    }
}