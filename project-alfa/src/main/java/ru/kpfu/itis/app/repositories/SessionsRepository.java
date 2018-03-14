package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.Institute;
import ru.kpfu.itis.app.model.Session;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 01.03.2018
 */
public interface SessionsRepository extends JpaRepository<Session,Long> {
    public Session findOneBySemesterNumberAndInstitute(Byte semesterNumber, Institute institute);
}
