package ru.kpfu.itis.app.model;

import lombok.*;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class AmazonFormCredentials {
    private String expirationDate;
    private String key;
    private String acl;
    private String contentType;
    private String serverSideEncryption;
    private String amzCredential;
    private String amzAlgorithm;
    private String amzDate;
    private String policy;
    private String signature;
    private String serverUrl;
}
