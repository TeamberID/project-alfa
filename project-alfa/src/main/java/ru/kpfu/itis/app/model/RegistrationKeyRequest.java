package ru.kpfu.itis.app.model;

import lombok.*;
import ru.kpfu.itis.app.forms.RegistrationKeyRequestForm;

import javax.persistence.*;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
public class RegistrationKeyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RegistrationKeyRequestStatus status;

    private String name;
    private String surname;
    private String email;
    private Integer countOfKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id")
    private Institute institute;

    private byte course;

    @Column(name = "\"group\"")
    private String group;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_doc_id")
    private Image documentImage;

    private long date;
}
