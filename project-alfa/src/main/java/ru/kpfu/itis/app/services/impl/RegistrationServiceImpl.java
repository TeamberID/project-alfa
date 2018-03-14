package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.UserRegistrationForm;
import ru.kpfu.itis.app.model.*;
import ru.kpfu.itis.app.repositories.RegistrationKeyRepository;
import ru.kpfu.itis.app.repositories.UserDataRepository;
import ru.kpfu.itis.app.repositories.UsersRepository;
import ru.kpfu.itis.app.security.role.Role;
import ru.kpfu.itis.app.security.status.UserStatus;
import ru.kpfu.itis.app.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RegistrationKeyRepository registrationKeyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegistrationForm userForm) {
        RegistrationKey registrationKey = registrationKeyRepository.findByValue(userForm.getKey());
        RegistrationKeyRequest registrationKeyRequest = registrationKey.getKeyRequest();

        User user = User.builder()
                .name(userForm.getLogin())
                .university(registrationKeyRequest.getUniversity())
                .institute(registrationKeyRequest.getInstitute())
                .course(registrationKeyRequest.getCourse())
                .build();
        usersRepository.save(user);

        UserData newUserData = UserData.builder()
                .login(userForm.getLogin())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .role(Role.USER)
                .userStatus(UserStatus.CONFIRMED)
                .user(user)
                .build();
        userDataRepository.save(newUserData);
    }
}