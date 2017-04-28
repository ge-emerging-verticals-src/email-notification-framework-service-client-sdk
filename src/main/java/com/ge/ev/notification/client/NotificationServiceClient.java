package com.ge.ev.notification.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ev.notification.client.requests.NotificationRequest;
import com.ge.ev.notification.client.response.NotificationServiceResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by 212391398 on 4/18/17.
 */
public class NotificationServiceClient {

  private static final ObjectMapper mapper = new ObjectMapper();

  private String tenantUuid = null;
  private String version = null;
  private String baseUrl = null;
  private String token = null;

  public String getTenantUuid() {
    return tenantUuid;
  }

  public void setTenantUuid(String tenantUuid) {
    this.tenantUuid = tenantUuid;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public NotificationServiceResponse sendRequest(NotificationRequest notificationRequest) throws IOException
  {
    HttpClient client = HttpClientBuilder.create().build();
    NotificationServiceResponse notificationServiceResponse = null;
    try {
      HttpRequestBase requestBase = notificationRequest.getRequest();

      HttpResponse response = client.execute(requestBase);

      if (response != null)
      {
        HttpEntity entity = response.getEntity();
        if (entity != null)
        {
          BufferedInputStream bufferedInputStream = new BufferedInputStream(response.getEntity().getContent());
          String json = IOUtils.toString(bufferedInputStream);
          notificationServiceResponse = mapper.readValue(json, NotificationServiceResponse.class);
          bufferedInputStream.close();
        }
      }
    } catch (Exception ex){
      ex.printStackTrace();
    }

    return notificationServiceResponse;
  }



}
