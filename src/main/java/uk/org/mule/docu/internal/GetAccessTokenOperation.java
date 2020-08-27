package uk.org.mule.docu.internal;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.auth.OAuth;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.exception.ModuleException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mule.runtime.extension.api.annotation.param.MediaType.TEXT_PLAIN;

public class GetAccessTokenOperation {
  private static final String CLIENT_ID = "480bf239-9265-4f94-a333-5b1eebde0300";
  private static final String WWW_PRIVATE_KEY =
          "-----BEGIN RSA PRIVATE KEY-----\r\n" +
          "MIIEowIBAAKCAQEAnAwdbfRws+AUhbLTRPTpY9WtYqnT58PyfO06uXOXjBcyX+V0\n" +
          "RSFpZaaS0fojhZ8gafG9uHrzQzD5ZQIPNWog5d8JW1n2TVQig/b/0fwJkRv+0/fI\n" +
          "bakI61h+S+eqc7pU/BnxDJvGg4BEUMmFbYBfbAVgVLtO6yHXtf9v3vS/pkobUrNl\n" +
          "yoHdVd2ca2Ft084i+KdmDOaez9A2LXHX3NNjCYggwUYg4XyBYQOyUNyJq5AtVG4z\n" +
          "axOq1eLL1z69obVL4D8nTIPg3KesUfiVLeKGBYEv7eIB7Ohlrd2+xse+JXxLGaI2\n" +
          "E2hQDi17Jnq0s4DOQKgzzzx4S1jVS3ifhvMHuQIDAQABAoIBAEMKgWpH5lOhJa/7\n" +
          "EAFEJzEZEBCfXcbozDkXwm8CVk19ecrxTW6V2C4vzkwf5zr1Dnv1u8iSKgjn9X2L\n" +
          "0PTlz5RyqhZxI/w89Uc0yttwKLwLBwQSJC3YvVg/AKOjt/tYRkK48nK0Sikc5jy/\n" +
          "F2/gAx7pgGVmw5UJSR+LyohDJkNOTOOqK+gL9peI+170b/cJHVyzKTiKiV+rv8pC\n" +
          "cYDQ46cqllMbpU1Ajo2vCAi9RBn7pwfoD49m5BANxhO1vpv795oeAQFJhAxOWAHq\n" +
          "jdYHoGzRmnkapt2hiFwYyJDWARJ+XcxVDaUQcp3o7OqLiYIWok8K7EXIAbnlekEw\n" +
          "ax7UMDECgYEA7i/Y38FjzXcQSIFu0TWYDYJymeofD1nsoUzkzixIo8tSlp+3jYsB\n" +
          "A5fTfkrEE5XzyCsBqb9Wrx2CwVtKsGf8223FUPlDOUj+dOYm9YknJ9zLsyz8uLf0\n" +
          "HH+Wj3VgCpRxnknFpJOM/beqs50pm6xNOaWGUi5tXEOmlNtxkEDJJ3cCgYEAp7eu\n" +
          "69kcnp9XpfZjOCwTflU3COksbhcaB9IXmuq/JnUIbb99HWK4xkjv/yVJxQxEMQyg\n" +
          "zfnx+j0axn9WVd/nCAy833XQ/X/tZ7dxUj7QL0/cFMAfJiUOtTe5OztzilHfyyfG\n" +
          "gsmFWUNP8k+Yap/8S9pnIj/GRr8AlrdufV6Jdk8CgYEAuBXcvNp/Yv4BZLg8SI7u\n" +
          "TIqkPdaXWMr8OpHuSHYrUgRDeUxJlah4CSnfeD0am7SKLAspBuNtgHwDYaNH71z/\n" +
          "LSwCkxmC3hRPLVPvilcNHx7jhEBhtfXeQeROHePubV7bDSYhDZhIIJNB5/2webNK\n" +
          "ICKQ1JUzSlqytVuAwqd/j/MCgYB6IwvheTScV1HO7IUI7SAtCgWQGa5fZbdxJpBG\n" +
          "ItmdOfEvaxnEApao34jW9+Km+u3WAmpAVdrf7wvGrgjDdNCcThplaTkOwtCyjSab\n" +
          "TF7zdWbCPb7xWl9BnRjph7v2aPVeztKDgBn6DUj1Hv3R+DwVSTF+AY91Cbd2CpMN\n" +
          "/YCBhQKBgAobaPSjegjsa04M+HBEGe6KzYJOxI1JnviDFenhzo0Y57fkIIucDIjC\n" +
          "Zbs0fDPfMxE+CWdMSBHRLoGHlwR5sRY+FvKm+B6k55bu18zYNL4gbgnpT1ym4aFN\n" +
          "7qtzKflQJNGEUIZy9bU4iSdvaBrAtbPi34E6w6OuFdJqZFVY0C7t" +
          "\r\n-----END RSA PRIVATE KEY-----";
  private static final String DEMO_PRIVATE_KEY =
          "-----BEGIN RSA PRIVATE KEY-----\r\n" +
          "MIIEpAIBAAKCAQEAhAn7K5ndu58DVYBopKPaQaDcthLxN8Hw8uady3olhVO0ZZ2O" +
          "z+KuFbiAej81Fg3NOEkTrMJSSxJpWIbVEoieeCY7SVdUBg50R5MzJTXGqpqLwQoA" +
          "alVnPHBuKK8GmIOKxcBv7rV7AAYJjVHmCbCtxxzrQmY5MxszNaL1Y+7WNI8MVS2q" +
          "Goklmvmw3vqdfChLe0yeNRedN92e7HGcq/De06BLY0W/aPKMGCZFhG/b225Z+6TH" +
          "VoNGmo0XjmpLBLrZzTJd+V12AIga7UllnHLusZt2u8+4ErkMzPJG+mU1ZzRm3Cxu" +
          "nUh37h4KEyt6GIpZr6CgGCTcZoxQxOp2R/ZhrwIDAQABAoIBABIz5A395M0S8102" +
          "r3Zpu6g02n3j/PV7Bad0NohmJZph+z/PqZqsJ+QJBEvradCer3jVI41mA/1NzlBs" +
          "1MlSJJ1GEVdKNDmvhW/r8ZmYIR7E4S5dcsg1+jOhVkvChqNrmFaBxzc+ip9ACydn" +
          "L9I3KrCFTUOL9H0WALMiF41NevdSFu5NLUA4qrz0THtp3ThCEge1+8oZwiKIvE0M" +
          "lCvFQtn9ISgOgudHzoE7chUZ0rHD9dWa6r3xC1Lf4aMy6jS9YMyI/3UqnQBLyYHL" +
          "FhZYIlESeUbnYZDHTrj1Zi7TMCX3RDCByy18hi2wLok0+f3zFmiay92FWCVkswZw" +
          "K3KnKo0CgYEAyf3mVJ+vya2SROzZFOzV4myVt6bwuMqWbo0jG4UeQIQbd4356fw1" +
          "Hn3USNGL7NLq1SgCwVqTZ/S5WsuUxIG3+6Df2i+YA4vfqUpBZkeNuGXjiikWPiAy" +
          "L0lFNhYBlOCJAmfcauha+vB6N9ecGe1AAdrPHkCZNtqKczwjSaICZEMCgYEAp1fk" +
          "7P0jsRpdkolYCUzYU5hpYaE13rf0Df5/4/f/KD8mvhEZ+Z06ngVoglHPA6iRW4lN" +
          "vIjyRBRBjtGUsrqAoqnBtv+l2VxZF9xMQ5Pxg6dAHWIdz4hjaepDbNndz+W+mowR" +
          "KHG9fnB01yzt9MvrApEH9AzsGlwXxAdypngeTCUCgYEAmMrMflrEuEPpjuWyV9fG" +
          "+p20qtVG5iGopTtxwRFzDs5fkJH+53X2IHiCKgsIUz5AkLjRTL1dH5fI1duFTxfo" +
          "uhi4++xgit41c3NOATZDB6da9VmbD6d/DrHX/s+qppkqSn2SB/R3Tba2XKNl217R" +
          "uhVv8qxeuVWawQxOjRZVxM0CgYBH6hnTTqX8AhycULGorYF5y1HeRu1jnSIgsXI8" +
          "geMBZMI9hBPplYwPRLu45XdVVyfAcq0ZjLCwWRQjUCXa+RM21EUPY6XeVzVib6XG" +
          "lNmBfmqrNfMhsLcoWShIqwgLw+u5duAYxWht4d4UapaIUOnZ3IHSe4sreNAtyhWX" +
          "PJ+okQKBgQCEUyq5JKxIvOKC2r8OI+wGziMz+Ebvih9KtQSmwyVT/vlgTJDVx14K" +
          "kWymTya5qhkwL4K6R5xQMcU1bQfx6lJGDJ+Bipp0iHlJXfyxsuv2yj2mo7TzdF4r" +
          "ROzU1yij3rfOQ4dd8icV1EnVnmZfj8ApfWh0qGJ1P4XRKcJXQcO9nw==" +
          "\r\n-----END RSA PRIVATE KEY-----";

  private static final String CONSENT_REQUIRED = "consent_required";
  private static final String DEMO_ACCOUNT = "account-d";
  private static final long EXPIRATION = 3600;
  private static final List<String> SCOPES =
          new ArrayList<String>(Arrays.asList(OAuth.Scope_SIGNATURE, OAuth.Scope_IMPERSONATION));

  @MediaType(value = TEXT_PLAIN)
  @Throws(DocuErrorsProvider.class)
  public String getUserAccessToken(@Config DocuConfiguration config) {
    String result = null;
    String privateKey = null;
    try {
      if (config != null) {
        String authURI = config.getAuthURI();
        ApiClient api = new ApiClient();
        api.setOAuthBasePath(authURI);
        if (authURI != null && authURI.toLowerCase().contains(DEMO_ACCOUNT)) {
          privateKey = DEMO_PRIVATE_KEY;
        }
        else {
          privateKey = WWW_PRIVATE_KEY;
        }
        OAuth.OAuthToken oAuthToken = api.requestJWTUserToken(CLIENT_ID, config.getUserId(), SCOPES, privateKey.getBytes(), EXPIRATION);
        if (oAuthToken != null) {
          result = oAuthToken.getAccessToken();
        }
      }
    }
    catch(Exception e) {
      if (e instanceof ApiException) {
        ApiException api = (ApiException)e;
        String body = api.getResponseBody();
        if (body != null && body.contains(CONSENT_REQUIRED)) {
          throw new ModuleException(DocuErrorType.CONSENT_REQUIRED, e);
        }
      }
      throw new ModuleException(DocuErrorType.INVALID_REQUEST, e);
    }
    return result;
  }
}
