package ru.kpfu.itis.app.utils;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.app.model.AmazonFormCredentials;

import java.util.Date;

import static ru.kpfu.itis.app.utils.Encoder.*;
import static ru.kpfu.itis.app.utils.DateConverter.*;
/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class AmazonClient {

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @Value("${amazonProperties.expirationDate}")
    private String expirationDate;

    @Value("${amazonProperties.serverSideEncryption}")
    private String serverSideEncryption;

    @Value("${amazonProperties.acl}")
    private String acl;

    @Value("${amazonProperties.amzAlgorithm}")
    private String amzAlgorithm;

    @Value("${amazonProperties.region}")
    private String region;

    @SneakyThrows
    public AmazonFormCredentials getCredentials() {
        String key = "";
        String contentType = "multipart/form-data";
        Date currentDate = new Date();

        String amzCredential = createS3AmzCredential(currentDate);
        String amzDate = createAmzDate(currentDate);

        String policy = createPolicy(key, contentType, amzCredential, amzDate);
        String signature = createSignature(policy, createShortDate(currentDate));
        String serverUrl = createServerUrl();

        return AmazonFormCredentials.builder()
                .expirationDate(expirationDate)
                .key(key)
                .acl(acl)
                .contentType(contentType)
                .serverSideEncryption(serverSideEncryption)
                .amzCredential(amzCredential)
                .amzAlgorithm(amzAlgorithm)
                .amzDate(amzDate)
                .policy(policy)
                .signature(signature)
                .serverUrl(serverUrl)
                .build();
    }

    private String createS3AmzCredential(Date date) {
        return accessKey + "/" + createShortDate(date) + "/" + region + "/s3/aws4_request";
    }

    private String createAmzDate(Date date) {
        return getISO8601StringForDate(date);
    }

    @SneakyThrows
    private String createPolicy(String key, String contentType, String amzCredential, String amzDate) {
        String rawPolicy =  "{ \"expiration\": \"" + expirationDate + "\",\n" +
                "  \"conditions\": [\n" +
                "    {\"bucket\": \"" + bucketName + "\"},\n" +
                "    [\"starts-with\", \"$key\", \"" + key + "\"],\n" +
                "    {\"acl\": \"" + acl + "\"},\n" +
                "    [\"starts-with\", \"$Content-Type\", \"" + contentType + "\"],\n" +
                "    {\"x-amz-server-side-encryption\": \"" + serverSideEncryption + "\"},\n" +
                "\n" +
                "    {\"x-amz-credential\": \"" + amzCredential + "\"},\n" +
                "    {\"x-amz-algorithm\": \"" + amzAlgorithm + "\"},\n" +
                "    {\"x-amz-date\": \"" + amzDate + "\" }\n" +
                "  ]\n" +
                "}";
        return base64String(rawPolicy.getBytes("UTF-8")).replaceAll("\n", "").replaceAll("\r", "");
    }

    @SneakyThrows
    private String createSignature(String policy, String shortDate) {
        byte[] signingKey = getSignatureKey(secretKey, shortDate, region, "s3");
        byte[] rawSignature = hmacSHA256(policy, signingKey);
        return hexString(rawSignature);
    }

    private byte[] getSignatureKey(String key, String dateStamp, String regionName, String serviceName) throws Exception {
        byte[] kSecret = ("AWS4" + key).getBytes("UTF8");
        byte[] kDate = hmacSHA256(dateStamp, kSecret);
        byte[] kRegion = hmacSHA256(regionName, kDate);
        byte[] kService = hmacSHA256(serviceName, kRegion);
        return hmacSHA256("aws4_request", kService);
    }

    private String createServerUrl() {
        return "http://" + bucketName + ".s3.amazonaws.com/";
    }

    private String createShortDate(Date date) {
        return getShortDateStringForDate(date);
    }

    public String getBucketUrl() {
        return endpointUrl + "/" + bucketName;
    }
}
