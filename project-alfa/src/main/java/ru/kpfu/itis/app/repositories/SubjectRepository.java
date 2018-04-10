package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
