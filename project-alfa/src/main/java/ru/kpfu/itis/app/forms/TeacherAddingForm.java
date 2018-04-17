package ru.kpfu.itis.app.forms;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private String name;
    private MultipartFile photo;
    private List<Long> subjectsId;
}
