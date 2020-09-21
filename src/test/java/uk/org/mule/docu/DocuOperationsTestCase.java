package uk.org.mule.docu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.junit.Test;

public class DocuOperationsTestCase extends MuleArtifactFunctionalTestCase {
  /**
   * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
   */
  @Override
  protected String getConfigFile() {
    return "test-docu-config.xml";
  }

  @Test
  public void executeGetDemoUserAccessToken() throws Exception {
    String payloadValue = ((String) flowRunner("getDemoUserAccessToken").run()
                                      .getMessage()
                                      .getPayload()
                                      .getValue());
    assertThat(payloadValue, is(notNullValue()));
  }

  @Test
  public void executeGetProdUserAccessToken() throws Exception {
    String payloadValue = ((String) flowRunner("getProdUserAccessToken").run()
                                      .getMessage()
                                      .getPayload()
                                      .getValue());
    assertThat(payloadValue, is(notNullValue()));
  }

  @Test
  public void executeUnknownHost() throws Exception {
    String errorMessage = null;
    try {
      flowRunner("unknownHost").run();
    }
    catch(Exception e) {
      errorMessage = e.getMessage();
    }
    assertThat(errorMessage, is(notNullValue()));
    assertThat(errorMessage, containsString("UnknownHost"));
  }

  @Test
  public void executeConsentRequired() throws Exception {
    String errorMessage = null;
    try {
      flowRunner("consentRequired").run();
    }
    catch(Exception e) {
      errorMessage = e.getMessage();
    }
    assertThat(errorMessage, is(notNullValue()));
    assertThat(errorMessage, containsString("consent_required"));
  }
}
