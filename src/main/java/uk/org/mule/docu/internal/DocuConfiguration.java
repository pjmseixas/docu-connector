package uk.org.mule.docu.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(GetAccessTokenOperation.class)
public class DocuConfiguration {
  @Parameter
  @DisplayName("OAuth Base Path")
  @Placement(order = 1)
  private String authURI;

  @Parameter
  @DisplayName("User ID")
  @Placement(order = 2)
  private String userId;

  public String getAuthURI() {
    return authURI;
  }

  public String getUserId() {
    return userId;
  }
}
