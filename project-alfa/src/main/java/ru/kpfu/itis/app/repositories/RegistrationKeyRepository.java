package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.RegistrationKey;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface RegistrationKeyRepository extends JpaRepository<RegistrationKey, Long>{
    RegistrationKey findByValue(String value);
}
