package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.University;

import java.util.Optional;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 01.03.2018
 */
public interface UniversitiesRepository extends JpaRepository<University,Long> {
    Optional<University> findById(Long id);
}
