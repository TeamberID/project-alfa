package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.UserEditForm;
import ru.kpfu.itis.app.model.User;
import ru.kpfu.itis.app.model.UserData;
import ru.kpfu.itis.app.repositories.UserDataRepository;
import ru.kpfu.itis.app.repositories.UsersRepository;
import ru.kpfu.itis.app.security.role.Role;
import ru.kpfu.itis.app.security.status.UserStatus;
import ru.kpfu.itis.app.services.UserDatasService;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 19.03.2018
 */
@Service
public class UserDatasServiceImpl implements UserDatasService {
    @Autowired
    private UserDataRepository userDatasRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserData> getAll() {
        return userDatasRepository.findAll();
    }

    @Override
    public void add(UserEditForm userEditForm) {
        userDatasRepository.save(UserData.builder()
                .id(Long.parseLong(userEditForm.getId()))
                .login(userEditForm.getLogin())
                .hashPassword(userEditForm.getHashPassword())
                .role(Role.valueOf(userEditForm.getRole()))
                .userStatus(UserStatus.valueOf(userEditForm.getUserStatus()))
                .user(User.builder()
                        .id(Long.parseLong(userEditForm.getUser_id()))
                        .name(userEditForm.getName())
                        .course(Byte.parseByte(userEditForm.getCourse()))
                        .build())
                .build());
    }

    @Override
    public UserData getById(Long id) {
        return userDatasRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        Long userId = userDatasRepository.findOne(id).getUser().getId();
        userDatasRepository.delete(id);
        usersRepository.delete(userId);
    }
}
