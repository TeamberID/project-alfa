package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.UserRegistrationForm;
import ru.kpfu.itis.app.model.RegistrationKey;
import ru.kpfu.itis.app.model.RegistrationKeyRequest;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.RegistrationKeyRepository;
import ru.kpfu.itis.app.repositories.UserDatasRepository;
import ru.kpfu.itis.app.security.role.Role;
import ru.kpfu.itis.app.security.status.UserStatus;
import ru.kpfu.itis.app.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserDatasRepository usersRepository;

    @Autowired
    private RegistrationKeyRepository registrationKeyRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(UserRegistrationForm userForm) {
        RegistrationKey registrationKey = registrationKeyRepository.findByValue(userForm.getKey());
        RegistrationKeyRequest registrationKeyRequest = registrationKey.getKeyRequest();

        UserData newUser = UserData.builder()
                .login(userForm.getLogin())
                .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                .university(registrationKeyRequest.getUniversity())
                .institute(registrationKeyRequest.getInstitute())
                .course(registrationKeyRequest.getCourse())
                .group(registrationKeyRequest.getGroup())
                .role(Role.USER)
                .userStatus(UserStatus.CONFIRMED)
                .build();
        usersRepository.save(newUser);
    }
}