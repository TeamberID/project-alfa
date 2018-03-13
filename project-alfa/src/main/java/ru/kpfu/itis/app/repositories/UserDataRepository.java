package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.UserData;

import java.util.Optional;


public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findOneByLogin(String login);
}