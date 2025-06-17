package com.sprint.mission.discodeit.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class S3LocalStackTest {

  @Container
  static LocalStackContainer localstack = new LocalStackContainer("3.5.0")
      .withServices(LocalStackContainer.Service.S3);

  final String bucketName = "test-bucket";

  S3Client s3Client;

  @BeforeEach
  void setUp() {
    s3Client = S3Client
        .builder()
        .endpointOverride(localstack.getEndpoint())
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(localstack.getAccessKey(), localstack.getSecretKey())
            )
        )
        .region(Region.of(localstack.getRegion()))
        .build();
    s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
  }

  @Test
  void testPutAndGetObject() {
    String key = "sample.txt";
    String content = "hello localstack";

    s3Client.putObject(
        PutObjectRequest.builder().bucket(bucketName).key(key).build(),
        software.amazon.awssdk.core.sync.RequestBody.fromString(content)
    );

    String result = s3Client.getObjectAsBytes(GetObjectRequest.builder()
            .bucket(bucketName).key(key).build())
        .asUtf8String();

    assertEquals(content, result);
  }
}
