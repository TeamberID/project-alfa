package ru.kpfu.itis.app.forms;

import lombok.*;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 21.03.2018
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEditForm {
    private String id;
    private String user_id;
    private String login;
    private String name;
    private String role;
    private String userStatus;
    private String course;

}