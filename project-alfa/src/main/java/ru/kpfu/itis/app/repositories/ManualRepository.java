package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.Manual;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface ManualRepository extends JpaRepository<Manual, Long>{
}
