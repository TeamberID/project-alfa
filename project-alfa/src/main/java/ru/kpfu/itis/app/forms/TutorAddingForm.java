package ru.kpfu.itis.app.forms;

import lombok.*;

import java.util.List;

/**
 * Created by Timur Iderisov on 02.05.2018.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TutorAddingForm {
    private String name;
    private String contacts;
    private List<Long> subjectsId;
}
