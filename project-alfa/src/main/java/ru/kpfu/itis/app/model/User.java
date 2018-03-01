package ru.kpfu.itis.app.model;

import lombok.*;
import ru.kpfu.itis.app.security.role.Role;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String login;

    private String hashPassword;


    private String university;

    private String institute;

    private String course;



    @Enumerated(EnumType.STRING)
    private Role role;
}