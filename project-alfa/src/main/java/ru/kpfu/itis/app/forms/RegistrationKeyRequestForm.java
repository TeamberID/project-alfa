package ru.kpfu.itis.app.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationKeyRequestForm {
    private String name;
    private String surname;
    private String email;
    private Integer countOfKey;

    private Long universityId;
    private Long instituteId;
    private byte course;
    private String group;

    private String documentStorageName;
}
