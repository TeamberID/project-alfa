package ru.kpfu.itis.app.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.app.model.AmazonFormCredentials;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class AmazonClient {

    private AmazonS3 s3client;

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

    @Value("${amazonProperties.amzDate}")
    private String amzDate;

    @Value("${amazonProperties.amzCredentialTail}")
    private String amzCredentialTail;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    @SneakyThrows
    public AmazonFormCredentials getCredentials() {
        String key = "";
        String contentType = "multipart/form-data";
        String amzCredential = accessKey + amzCredentialTail;

        String policy = createPolicy(key, contentType, amzCredential);
        String signature = createSignature(policy);
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

    public String saveFileToStorage(MultipartFile multipartFile, String fileName) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileToS3Bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    public void deleteFileFromStorage(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        deleteFileFromS3Bucket(fileName);
    }

    @SneakyThrows
    private String createPolicy(String key, String contentType, String amzCredential) {
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
        return (new BASE64Encoder()).encode(rawPolicy.getBytes("UTF-8")).replaceAll("\n", "").replaceAll("\r", "");

    }

    private String createServerUrl() {
        return "http://" + bucketName + ".s3.amazonaws.com/";
    }

    @SneakyThrows
    private String createSignature(String policy) {
        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256"));
        return (new BASE64Encoder()).encode(hmac.doFinal(policy.getBytes("UTF-8"))).replaceAll("\n", "");
    }

    private void deleteFileFromS3Bucket(String fileName) {
        s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
    }

    private void uploadFileToS3Bucket(String fileName, File file) {
        s3client.putObject(
                new PutObjectRequest(bucketName, fileName, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }

    @SneakyThrows
    private File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
