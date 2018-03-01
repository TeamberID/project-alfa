package ru.kpfu.itis.app.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.RegistrationKeyRequestForm;
import ru.kpfu.itis.app.model.*;
import ru.kpfu.itis.app.repositories.RegistrationKeyRepository;
import ru.kpfu.itis.app.repositories.RegistrationKeyRequestRepository;
import ru.kpfu.itis.app.services.RegistrationKeyRequestService;
import ru.kpfu.itis.app.utils.EmailSender;
import ru.kpfu.itis.app.utils.FileStorageUtil;
import ru.kpfu.itis.app.utils.impl.RegistrationKeyGenerator;

import java.util.List;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Service
public class RegistrationKeyRequestServiceImpl implements RegistrationKeyRequestService {

    private RegistrationKeyRequestRepository keyRequestRepository;
    private RegistrationKeyRepository keyRepository;
    private RegistrationKeyGenerator keyGenerator;
    private EmailSender emailSender;
    private FileStorageUtil fileStorageUtil;

    public RegistrationKeyRequestServiceImpl(RegistrationKeyRequestRepository keyRequestRepository, RegistrationKeyRepository registrationKeyRepository, RegistrationKeyGenerator keyGenerator, EmailSender emailSender, FileStorageUtil fileStorageUtil) {
        this.keyRequestRepository = keyRequestRepository;
        this.keyRepository = registrationKeyRepository;
        this.keyGenerator = keyGenerator;
        this.emailSender = emailSender;
        this.fileStorageUtil = fileStorageUtil;
    }

    @Override
    public void saveKeyRequest(RegistrationKeyRequestForm requestForm) {
        RegistrationKeyRequest registrationKeyRequest = getRegistrationKeyRequestByForm(requestForm);
        fileStorageUtil.saveDocumentImageToStorage(requestForm.getDocumentImageMultipartFile(), registrationKeyRequest.getDocumentImage());
        keyRequestRepository.save(registrationKeyRequest);
    }

    private RegistrationKeyRequest getRegistrationKeyRequestByForm(RegistrationKeyRequestForm requestForm) {
        RegistrationKeyRequest registrationKeyRequest = RegistrationKeyRequest.builder()
                .name(requestForm.getName())
                .surname(requestForm.getSurname())
                .email(requestForm.getEmail())
                .countOfKey(requestForm.getCountOfKey())
                .university(requestForm.getUniversity())
                .institute(requestForm.getInstitute())
                .course(requestForm.getCourse())
                .group(requestForm.getGroup())
                .status(RegistrationKeyRequestStatus.UNDER_CONSIDERATION)
                .date(System.currentTimeMillis())
                .build();
        FileInfo documentFileInfo = fileStorageUtil.getDocFileInfoByMultipart(requestForm.getDocumentImageMultipartFile());
        Image documentImage = Image.builder().fileInfo(documentFileInfo).build();
        registrationKeyRequest.setDocumentImage(documentImage);
        return registrationKeyRequest;
    }

    @Override
    public void acceptKeyRequest(Long requestId) {
        RegistrationKeyRequest keyRequest = keyRequestRepository.findOne(requestId);
        keyRequest.setStatus(RegistrationKeyRequestStatus.ACCEPTED);
        keyRequestRepository.save(keyRequest);

        List<RegistrationKey> registrationKeys = keyGenerator.getRegistrationKeys(keyRequest);
        keyRepository.save(registrationKeys);

        sendAcceptEmail(keyRequest, registrationKeys);
    }

    @Override
    public void denyKeyRequest(Long requestId) {
        RegistrationKeyRequest keyRequest = keyRequestRepository.findOne(requestId);
        keyRequest.setStatus(RegistrationKeyRequestStatus.DENIED);
        keyRequestRepository.save(keyRequest);

        sendDenyEmail(keyRequest);
    }

    private void sendDenyEmail(RegistrationKeyRequest keyRequest) {
        emailSender.sendEmailMessage(
                keyRequest.getEmail(),
                "PROJECT NAME ALFA REGISTRATION KEYS DENY EMAIL",
                getDenyMessage(keyRequest)
        );
    }

    private void sendAcceptEmail(RegistrationKeyRequest keyRequest, List<RegistrationKey> registrationKeys) {
        emailSender.sendEmailMessage(
                keyRequest.getEmail(),
                "PROJECT NAME ALFA REGISTRATION KEYS ACCEPT EMAIL",
                getAcceptMessage(keyRequest, registrationKeys)
        );
    }

    private String getAcceptMessage(RegistrationKeyRequest keyRequest, List<RegistrationKey> registrationKeys) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder
                .append("<h2>Your request was accepted!</h2>").append("Welcome to the Alfa, ").append(keyRequest.getName())
                .append("<h3>Please, take your keys. Remember, you have 2 days to activate it!</h3>");

        messageBuilder.append("<ol>");
        for (RegistrationKey registrationKey: registrationKeys) {
            messageBuilder.append("<li>");
            messageBuilder.append(registrationKey.getValue());
            messageBuilder.append("</li>");
        }
        messageBuilder.append("</ol>");
        return messageBuilder.toString();
    }

    private String getDenyMessage(RegistrationKeyRequest keyRequest) {
        return "<h2>Your request was denied!</h2>" +
                "<p>Sorry " + keyRequest.getName() + ", but your personal data is invalid for this service.</p>" +
                "<p>Name Project Alfa Team</p>";
    }

    @Override
    public List<RegistrationKeyRequest> getUnderConsiderationRequests() {
        return keyRequestRepository.findByStatus(RegistrationKeyRequestStatus.UNDER_CONSIDERATION);
    }

    @Override
    public List<RegistrationKeyRequest> getAllRequests() {
        return keyRequestRepository.findAll();
    }
}
