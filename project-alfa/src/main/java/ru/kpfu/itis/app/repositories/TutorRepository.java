package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.Subject;
import ru.kpfu.itis.app.model.Tutor;

import java.util.List;

/**
 * Created by Timur Iderisov on 22.04.2018.
 */
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    List<Tutor> findAllBySubjects(Subject subject);
}
