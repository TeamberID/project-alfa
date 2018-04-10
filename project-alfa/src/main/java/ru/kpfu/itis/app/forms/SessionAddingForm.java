package ru.kpfu.itis.app.forms;

import lombok.*;

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
public class SessionAddingForm {
    byte semester_number;
    long institute_id;

}
