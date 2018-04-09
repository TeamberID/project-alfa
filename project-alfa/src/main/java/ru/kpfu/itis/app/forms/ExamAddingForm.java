package ru.kpfu.itis.app.forms;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExamAddingForm{
    private Long institute_id;
    private Byte semester_number;
    private Long session_id;
    private Long subject_id;
    private Long teacher_id;
}
