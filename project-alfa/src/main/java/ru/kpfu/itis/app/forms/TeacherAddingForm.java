package ru.kpfu.itis.app.forms;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 24.03.2018
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherAddingForm {
    @NotEmpty
    private String name;
    @NotEmpty
    private String teacherPhotoStorageName;
    @NotNull
    private List<Long> subjectsId;
}
