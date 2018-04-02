package ru.kpfu.itis.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.app.model.TeacherComment;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface TeacherCommentRepository extends JpaRepository<TeacherComment, Long> {
    List<TeacherComment> findByTeacherId(Long teacherId);
}
