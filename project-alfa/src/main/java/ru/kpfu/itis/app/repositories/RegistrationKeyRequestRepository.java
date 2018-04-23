package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.RegistrationKeyRequest;
import ru.kpfu.itis.app.model.status.RegistrationKeyRequestStatus;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface RegistrationKeyRequestRepository extends JpaRepository<RegistrationKeyRequest, Long> {
    List<RegistrationKeyRequest> findByStatus(RegistrationKeyRequestStatus status);
}
