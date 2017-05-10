package com.ge.ev.notification.client.requests.template;

import static com.ge.ev.notification.Constants.ALL_TEMPLATE_REQUEST_URL;
import static com.ge.ev.notification.Constants.BASEURL;
import static com.ge.ev.notification.Constants.TEMPLATE_FILE;
import static com.ge.ev.notification.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.Constants.TENANT_UUID;
import static com.ge.ev.notification.Constants.TOKEN;
import static com.ge.ev.notification.Constants.VERSION;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UploadTemplateRequestsTest {


  private static final String UPLOAD_TEMPLATE_REQUEST_URI = "/tenants/" + TENANT_UUID + "/templates/" + TEMPLATE_UUID + "/upload";

  private static final String UPLOAD_TEMPLATE_REQUEST_URL = ALL_TEMPLATE_REQUEST_URL + "/" + TEMPLATE_UUID + "/upload";

  @Test
  public void TestUploadTemplateRequests() {

    InputStream in = new ByteArrayInputStream(TEMPLATE_FILE.getBytes());

    UploadTemplateRequestBody uploadTemplateRequestBody = new UploadTemplateRequestBody.UploadTemplateRequestBodyBuilder(in)
        .build();

    try {
      assert(uploadTemplateRequestBody.getTemplateFileInputStream().available() > 0);
    } catch (IOException e) {
      e.printStackTrace();
    }

    UploadTemplateRequest uploadTemplateRequest = new UploadTemplateRequest.UploadTemplateRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setUploadTemplateRequestBody(uploadTemplateRequestBody)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(uploadTemplateRequest.getTenantUuid().equals(TENANT_UUID));
    assert(uploadTemplateRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(uploadTemplateRequest.getRequestUri().equals(UPLOAD_TEMPLATE_REQUEST_URI));
    assert(uploadTemplateRequest.getRequest().getMethod().equals("POST"));
    assert(uploadTemplateRequest.getRequest().getURI().toString().equals(UPLOAD_TEMPLATE_REQUEST_URL));
    assert(uploadTemplateRequest.getRequestUrl().equals(UPLOAD_TEMPLATE_REQUEST_URL));
    assert(uploadTemplateRequest.getToken().equals(TOKEN));
    assert(uploadTemplateRequest.getRequestBody().toJson().equals(uploadTemplateRequestBody.toJson()));
  }

  @Test
  public void TestUploadTemplateRequestBody() {

    InputStream in = new ByteArrayInputStream(TEMPLATE_FILE.getBytes());

    UploadTemplateRequestBody uploadTemplateRequestBody = new UploadTemplateRequestBody.UploadTemplateRequestBodyBuilder(in)
        .build();

    try {
      assert(uploadTemplateRequestBody.getTemplateFileInputStream().available() > 0);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
