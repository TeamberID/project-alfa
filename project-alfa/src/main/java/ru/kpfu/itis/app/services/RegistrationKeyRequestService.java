package ru.kpfu.itis.app.services;

import ru.kpfu.itis.app.forms.RegistrationKeyRequestForm;
import ru.kpfu.itis.app.model.RegistrationKeyRequest;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface RegistrationKeyRequestService {
    void saveKeyRequest(RegistrationKeyRequestForm requestForm);
    void acceptKeyRequest(Long requestId);
    void denyKeyRequest(Long requestId);
    List<RegistrationKeyRequest> getUnderConsiderationRequests();
    List<RegistrationKeyRequest> getAllRequests();
}
