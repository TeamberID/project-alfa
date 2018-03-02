package ru.kpfu.itis.app.model;

import lombok.*;

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
public class RegistrationKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private long expiration;

    @Enumerated(EnumType.STRING)
    private RegistrationKeyStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "key_request_id")
    private RegistrationKeyRequest keyRequest;
}
