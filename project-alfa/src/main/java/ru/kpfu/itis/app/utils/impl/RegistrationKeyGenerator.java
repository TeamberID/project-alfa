package ru.kpfu.itis.app.utils.impl;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.app.model.RegistrationKey;
import ru.kpfu.itis.app.model.RegistrationKeyRequest;
import ru.kpfu.itis.app.model.RegistrationKeyStatus;
import ru.kpfu.itis.app.utils.KeyGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class RegistrationKeyGenerator implements KeyGenerator {

    // 2 days
    public static long keyLifeTime = 172_800_000;

    public String generateUUIDValue() {
        return UUID.randomUUID().toString();
    }

    public List<RegistrationKey> getRegistrationKeys(RegistrationKeyRequest request) {
        int count = request.getCountOfKey();
        List<RegistrationKey> registrationKeys = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            registrationKeys.add(
                    RegistrationKey.builder()
                            .value(generateUUIDValue())
                            .expiration(System.currentTimeMillis() + keyLifeTime)
                            .status(RegistrationKeyStatus.NOT_USED)
                            .keyRequest(request)
                            .build()
            );
        }
        return registrationKeys;
    }
}
