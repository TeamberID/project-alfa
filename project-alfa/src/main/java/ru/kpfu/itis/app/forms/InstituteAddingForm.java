package ru.kpfu.itis.app.forms;

import lombok.*;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 23.03.2018
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstituteAddingForm {
    String name;
    Long universityId;
}
