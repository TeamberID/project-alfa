package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.User;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
}