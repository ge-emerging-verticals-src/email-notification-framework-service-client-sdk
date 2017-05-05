package com.ge.ev.notification.test;

import static com.ge.ev.notification.test.Constants.BASEURL;
import static com.ge.ev.notification.test.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.test.Constants.TENANT_UUID;
import static com.ge.ev.notification.test.Constants.TOKEN;
import static com.ge.ev.notification.test.Constants.VERSION;

import com.ge.ev.notification.client.requests.template.UploadTemplateRequest;
import com.ge.ev.notification.client.requests.template.UploadTemplateRequestBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/4/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UploadTemplateRequestBodyTest {

  @Test
  public void TestUploadTemplateRequestBody() {

    InputStream in = null;
    try {
      in = new FileInputStream(new File("/Users/212391398/source/emerging-verticals/poc/notification-blogpost/src/main/resources/template.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    UploadTemplateRequestBody uploadTemplateRequestBody = new UploadTemplateRequestBody.UploadTemplateRequestBodyBuilder(in)
        .build();

    try {
      assert(uploadTemplateRequestBody.getTemplateFileInputStream().available() > 0);
    } catch (IOException e) {
      e.printStackTrace();
    }

    UploadTemplateRequest uploadTemplateRequest = new UploadTemplateRequest.UploadTemplateRequestBuilder(
        BASEURL, VERSION, TENANT_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setUploadTemplateRequestBody(uploadTemplateRequestBody)
        .setToken(TOKEN)
        .build();

    for (Header header : uploadTemplateRequest.getRequest().getAllHeaders())
      System.out.println(header.getName() + ", " + header.getValue());


//    HttpClient client = HttpClientBuilder.create().build();
//    HttpPost post2 = new HttpPost("https://ev-notification-service-dev.run.aws-usw02-pr.ice.predix.io/v1/tenants/77e36836-cf4d-49a7-81fb-3a5311a454ff/templates/4728ec8e-ec91-4e36-9d10-1a621f8a82bd/upload");
//    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//    builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//    builder.addPart("file", new ByteArrayBody("Hello".getBytes(), ContentType.MULTIPART_FORM_DATA, ""));
////
//    org.apache.http.HttpEntity entity = builder.build();
//    post2.setEntity(entity);
//    post2.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImxlZ2FjeS10b2tlbi1rZXkiLCJ0eXAiOiJKV1QifQ.eyJqdGkiOiIwZGRiZmViMjM5OTQ0NDAyOGExNDYyYWRhNDNjYzM4ZiIsInN1YiI6ImRldiIsInNjb3BlIjpbImV2ZW50LmF1ZGl0IiwidWFhLm5vbmUiLCJldmVudC1hdWRpdC10cmFpbC56b25lLjE0YTZhZTEyLTVkY2UtNDRlYS04YjQyLTViYzA5ZGFmYTlhMC51c2VyIiwibm90aWZpY2F0aW9uLnpvbmUuZGEyODcxZTItMmRhZS00NmM2LWJkNDktZDk0ZTNhZGE0MDdkLnVzZXIiLCJub3RpZmljYXRpb24uem9uZS43N2UzNjgzNi1jZjRkLTQ5YTctODFmYi0zYTUzMTFhNDU0ZmYudXNlciJdLCJjbGllbnRfaWQiOiJkZXYiLCJjaWQiOiJkZXYiLCJhenAiOiJkZXYiLCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIiwicmV2X3NpZyI6ImZlOGM1OTFiIiwiaWF0IjoxNDkzOTE3OTk0LCJleHAiOjE0OTM5NjExOTQsImlzcyI6Imh0dHBzOi8vNmJhMzJjZjgtYmQ4Yi00NDIwLWIyNzEtZDMwOTdiYmVlMmNkLnByZWRpeC11YWEucnVuLmF3cy11c3cwMi1wci5pY2UucHJlZGl4LmlvL29hdXRoL3Rva2VuIiwiemlkIjoiNmJhMzJjZjgtYmQ4Yi00NDIwLWIyNzEtZDMwOTdiYmVlMmNkIiwiYXVkIjpbIm5vdGlmaWNhdGlvbi56b25lLjc3ZTM2ODM2LWNmNGQtNDlhNy04MWZiLTNhNTMxMWE0NTRmZiIsImRldiIsImV2ZW50LWF1ZGl0LXRyYWlsLnpvbmUuMTRhNmFlMTItNWRjZS00NGVhLThiNDItNWJjMDlkYWZhOWEwIiwibm90aWZpY2F0aW9uLnpvbmUuZGEyODcxZTItMmRhZS00NmM2LWJkNDktZDk0ZTNhZGE0MDdkIiwiZXZlbnQiXX0.LxexYOQbaJvC1BD92Yi0H_BOFf4H0KprzVupqlAl3z0Um-jJUHwNXIcg7t27_VxKbaQxEXyE3oZt9OYGfwmdVvatJo6K23FMdL1suPeHRiBeS3ZxGAxpG1iPxxVuaBUn5a2djlBTBnuxr1u8-oSW0psDRJQr4E3IehPMh_QkBwoRLvpD48T8lCJVuhaL_UkoXfXmW3miOz-OKAUHLkb-JWAMOOg5ZEahAhXFlOMq5NzwLw9V7YNuf7QJQ4SpyVpE8hTFExo0MZFRdPa-u-mCjpuQRaY7isS21uFbCaGqPdwDfscWJlBV9Mtr_ab8XvOYOKy2wsq7_IibpeDtRy3jYw");
//      for (Header header : post2.getAllHeaders())
//        System.out.println(header.getName() + ", " + header.getValue());

  }



}
