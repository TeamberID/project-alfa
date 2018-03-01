package ru.kpfu.itis.app.controllers.rest;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.app.model.RegistrationKeyRequest;
import ru.kpfu.itis.app.services.RegistrationKeyRequestService;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@RestController
@RequestMapping("/api/admin")
public class RegistrationKeyRequestAdminController {

    private RegistrationKeyRequestService keyRequestService;

    public RegistrationKeyRequestAdminController(RegistrationKeyRequestService keyRequestService) {
        this.keyRequestService = keyRequestService;
    }

    @GetMapping(value = "/accept-key-request/{id}")
    public List<RegistrationKeyRequest> acceptKeyRequest(@PathVariable("id") Long keyRequestId) {
        keyRequestService.acceptKeyRequest(keyRequestId);
        return keyRequestService.getUnderConsiderationRequests();
    }

    @GetMapping(value = "/deny-key-request/{id}")
    public List<RegistrationKeyRequest> denyKeyRequest(@PathVariable("id") Long keyRequestId) {
        keyRequestService.denyKeyRequest(keyRequestId);
        return keyRequestService.getUnderConsiderationRequests();
    }
}
