package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.Criteria;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface CriteriaRepository extends JpaRepository<Criteria, Long>{
}
