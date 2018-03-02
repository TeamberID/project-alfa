package ru.kpfu.itis.app.model;

import lombok.*;
import ru.kpfu.itis.app.security.role.Role;
import ru.kpfu.itis.app.security.status.UserStatus;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "userdata")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String login;

    private String hashPassword;


    private String university;

    private String institute;

    private String course;

    @Column(name = "\"group\"")
    private String group;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;


    @Enumerated(EnumType.STRING)
    private Role role;
}