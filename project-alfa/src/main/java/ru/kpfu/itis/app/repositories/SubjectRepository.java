package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.Subject;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 09.04.2018
 */

public interface SubjectRepository extends JpaRepository<Subject,Long> {

}
