package ru.kpfu.itis.app.utils;

import org.springframework.stereotype.Component;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

@Component
public class FileStorageUtil {

    private AmazonClient amazonClient;

    public FileStorageUtil(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    public String getFileBucketUrl(String storageName) {
        return amazonClient.getBucketUrl() + "/" + storageName;
    }
}
