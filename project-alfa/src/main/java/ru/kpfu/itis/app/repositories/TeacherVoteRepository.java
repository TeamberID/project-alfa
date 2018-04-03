package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.TeacherVote;

import java.util.Optional;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TeacherVoteRepository extends JpaRepository<TeacherVote, Long> {
    Optional<TeacherVote> findByTeacherIdAndUserId(Long teacherId, Long userId);
}
