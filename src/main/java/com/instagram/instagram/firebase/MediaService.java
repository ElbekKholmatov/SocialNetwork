package com.instagram.instagram.firebase;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import static com.instagram.instagram.firebase.MediaUtils.*;


@Service
public class MediaService {



    public String uploadByPath(String path, String fileUniqueId) throws IOException {
        StorageOptions
                .newBuilder()
                .setCredentials(getCredentials())
                .build()
                .getService()
                .createFrom(getBlobInfo(fileUniqueId, null), Paths.get(path));

        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileUniqueId, StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public String upload(MultipartFile file)  {
        String fileName= UUID.randomUUID()+file.getOriginalFilename();
        StorageOptions
                .newBuilder()
                .setCredentials(getCredentials())
                .build()
                .getService()
                .create(getBlobInfo(fileName, file.getContentType()), file.getBytes());
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    public String uploadByInputStream(InputStream inputStream, String fileUniqueId) throws IOException {
        StorageOptions
                .newBuilder()
                .setCredentials(getCredentials())
                .build()
                .getService()
                .createFrom(getBlobInfo(fileUniqueId, "image/jpeg"),
                        inputStream,
                        Storage.BlobWriteOption.detectContentType());
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileUniqueId, StandardCharsets.UTF_8));
    }

    private BlobInfo getBlobInfo(String fileUniqueId, String contentType) {
        return BlobInfo
                .newBuilder(BlobId.of(BUCKET_NAME, fileUniqueId))
                .setContentType(Objects.isNull(contentType) ? "media" : contentType)
                .build();
    }

    @SneakyThrows
    private Credentials getCredentials(){
        File file=new File(FIREBASE_TOKEN_PATH);
        return GoogleCredentials.fromStream(new FileInputStream(file));
    }

    private Credentials getCustomCredentials() throws IOException {
        return GoogleCredentials.fromStream(IOUtils.toInputStream("{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"sheengogram\",\n" +
                "  \"private_key_id\": \"3678470f12257078cbd5905b4ca76519ac178b97\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC8YxX2AeC7zsgt\\nuBf0pYOzh3o05q/1HLltxyFxUoWO3ELyGDAacA9xzcQOYyGQeN7iBXvcRJZMCyDT\\nZSJ7j5f0U8Gmj44NtTPqtQHPKHy+SiMxXItWjUYsIldjeuZCQ1A20k+DaZTe1MM9\\n3IY5aBoutjVOyjijB6h9gunvZJWy9A8maHrTb7nonGOGkt/0rA1rJIrYJ0b4PIvV\\nvGnUIfo2UCY+Bo9yeQqSOOrY2RjcevquOa6aNh+usvqPGXYX84QN3Smyn98kCSH3\\nS28UTbvsTfFUApMrECaKqQqjnV05xheaJLGkgJCOExUlREAmNvC8B2yZ/ZHrJOTp\\nAXaI0uTDAgMBAAECggEAEPDCw3cyblu57zfkGkEysVeNdb8I3lHRmug9Pvjcvxav\\ng8sJCFeWC/XPm5Dug79ay5hn+vbFTTFIUmTVz6ap/h6fW2BYCy4wQlA59Bb/9gdU\\nrYD/nAONkTUasVIWl/7VnRhsbgogiP6OUBHJ6tT3Bcb8zt5VxCimziKSRs9qEXLE\\nHkxZpK4tflCWC+/I1WdmRA7Y7/Qm+RYWowXIUEw+X7dXJIcxSSzG3RcDp1pD0kq1\\npTDL84Z07LylCwte7UEze7haCHPg8b53EZh+GVC1pCWMvJWLxcxGVMelv3E/M/bF\\nmXJsbyG8roKEls5TAQYv+89f6SIvwhrVB3YYa9i9AQKBgQDm3i6mht0rlfnf7G15\\nlXvrA7XJNIMzcG2j3z5wv38UfdzbDaZPuBbioBbJJz5WOZBjCYcrUVZZ4FC7vgDH\\nk49AZK8qns4o1h9XmHbujhhaT6amAP790E1OOUNRy6qVlxxuxE82zET76Uxs6o9J\\nQ64PmOSIPvhhqk2kbO1SrbHNmQKBgQDQ5QyKHlx7PwajuWaGWWLT1QYrkQBx8R2v\\n9xxotxLai0HhfkpzYB1Y3SSl0ap9+o2T8N62h1JQ51Ye+Z8VPMx8UrblyjzM/vAg\\nUaTKWYwSobpAzmWuk93jKSyBKloqVXzDPJSWWKjhjHwntV4fsBgCpKuItNGaLHTx\\nnabLgPUmuwKBgCGLEpgbbDMS/VUtktJhh7mjtlvfh1iH80Yl/3p+OEaP6vDg0NMw\\nWhLfU7AyRXpdrr7U+ZCvpq40S0ab2p0ksWGXv2Of4zBHvBIMEuuiNaOZgwsHhgT2\\nqOS+qStPPh+x7u2hFClQEI3RXctNYTgx3y5g9oNdMtVt2w4SzYB/wB+xAoGBAL/i\\nJmqZCQqNEmRCYdL3OLis7UYHpAkGGucQbqAhysfFM3cWsvzYRRzjxk4rEhk2leZX\\nU70HAK4gUhOR6HdxqcRplmfuJXuIkrqdRGo7Z1eWB6iP7wc1/JQ1thPP+5L3g+sz\\nMqoINtk4WrN+/j7NWsDS0l54+jBMtf9beWNlycsRAoGBANMH9sQHrH/vtKVkjILv\\nkv9hHWQPFejmVnsrMmd0v6/0kFOUdnwGDfnBKqPd40O4dDbKh8Fa8a0dsjyDeo5Y\\nQhM2lOkAgSY0edZZ7RphsLU33wriyZh6W3uNme/b2G+HmiN3DenlxHx8xy3v0OqZ\\noBcQeHxlszo35eitiz6gldVL\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"firebase-adminsdk-agrbi@sheengogram.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"104251587027707127521\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-agrbi%40sheengogram.iam.gserviceaccount.com\"\n" +
                "}\n", StandardCharsets.UTF_8));
    }

    public Object delete(String path) {
        try {
            String fileUniqueId = this.getFileNameFromPath(path);
            this.deleteFile(fileUniqueId);
            return ResponseEntity.ok();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }
    }

    public boolean deleteFile(String fileUniqueId) {
        return StorageOptions
                .newBuilder()
                .setCredentials(getCredentials())
                .build()
                .getService()
                .delete(BlobId.of(BUCKET_NAME, fileUniqueId));
    }

    private String getExtension(String fileUniqueId) {
        return fileUniqueId.substring(fileUniqueId.lastIndexOf("."));
    }

    private String getFileNameFromPath(String path) {
        return path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("?"));
    }

}
