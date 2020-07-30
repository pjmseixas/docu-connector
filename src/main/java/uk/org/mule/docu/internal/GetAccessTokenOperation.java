package uk.org.mule.docu.internal;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.auth.OAuth;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import uk.org.mule.docu.api.DocuException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mule.runtime.extension.api.annotation.param.MediaType.TEXT_PLAIN;

public class GetAccessTokenOperation {
  private static final String CLIENT_ID = "480bf239-9265-4f94-a333-5b1eebde0300";
  private static final String PRIVATE_KEY =
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

  private static final long EXPIRATION = 3600;
  private static final List<String> SCOPES =
          new ArrayList<String>(Arrays.asList(OAuth.Scope_SIGNATURE, OAuth.Scope_IMPERSONATION));

  @MediaType(value = TEXT_PLAIN)
  public String getUserAccessToken(@Config DocuConfiguration config) throws DocuException {
    String result = null;
    try {
      if (config != null) {
        ApiClient api = new ApiClient();
        api.setOAuthBasePath(config.getAuthURI());
        OAuth.OAuthToken oAuthToken = api.requestJWTUserToken(CLIENT_ID, config.getUserId(), SCOPES, PRIVATE_KEY.getBytes(), EXPIRATION);
        result = oAuthToken.getAccessToken();
      }
    }
    catch(Exception e) {
      throw new DocuException(e.getMessage(), e);
    }
    return result;
  }
}
