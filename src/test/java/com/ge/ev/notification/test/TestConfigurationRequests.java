package com.ge.ev.notification.test;

import com.ge.ev.notification.client.requests.configuration.ConfigurationRequestBody;
import com.ge.ev.notification.client.requests.configuration.DeleteConfigurationRequest;
import com.ge.ev.notification.client.requests.configuration.GetConfigurationsRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/1/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestConfigurationRequests {


  private static final String BASEURL = "https://notification.predix.io";
  private static final String VERSION = "v1";
  private static final String TENANT_UUID = "2beb4e8d-7eba-4cc1-b584-3d95bf84aedc";
  private static final String CONFIGURATION_UUID = "cd407863-e152-4b3f-9635-ce153311a12c";
  private static final String TOKEN = "8536aed6-0422-4e53-bb7d-4fc967128c50";

  private static final String BASE_REQUEST_URL = BASEURL + "/"  + VERSION + "/tenants/" + TENANT_UUID + "/configurations/";
  private static final String CONTENT_HEADER = "application/json";
  private static final String AUTHORIZATION_HEADER = "bearer " + TOKEN;

  private static final String CONFIGURATION_REQUEST_URL = BASEURL + "/"  + VERSION + "/tenants/" + TENANT_UUID + "/configurations/" + CONFIGURATION_UUID;

  private static final String PROTOCOL = "smtp";
  private static final String HOST = "smtp.mail.notification.ge.com";
  private static final Integer PORT = 587;
  private static final Boolean SMTP_AUTH =  true;
  private static final Boolean SMTP_START_TLS_ENABLE = true;
  private static final String MAIL_FROM  = "test@notification.ge.com";
  private static final String MAIL_USERNAME = "test";
  private static final String MAIL_PASSWORD = "test123";
  private static final String RETURN_PATH = "return.path";

  private static final String CONFIGURATION_REQUEST_BODY_JSON = "{\"protocol\":\"smtp\",\"host\":\"smtp.mail.notification.ge.com\",\"port\":587,\"smtpAuth\":true,\"smtpStarttlsEnable\":true,\"mailFrom\":\"test@notification.ge.com\",\"mailUsername\":\"test\",\"mailPassword\":\"test123\",\"mailReturnPath\":\"return.path\"}";

  @Test
  public void GetConfigurationRequestBuilderTest() {
    GetConfigurationsRequest getConfigurationsRequest = new GetConfigurationsRequest.GetConfigurationsRequestBuilder(BASEURL, VERSION, TENANT_UUID).setToken(TOKEN).build();

    assert(getConfigurationsRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getConfigurationsRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/"));
    assert(getConfigurationsRequest.getRequest().getMethod().equals("GET"));
    assert(getConfigurationsRequest.getRequest().getURI().toString().equals(BASE_REQUEST_URL));
    assert(getConfigurationsRequest.getRequestUrl().equals(BASE_REQUEST_URL));
    assert(getConfigurationsRequest.getToken().equals(TOKEN));

    HttpRequestBase requestBase = getConfigurationsRequest.getRequest();

    assert(requestBase!=null);
    assert(requestBase.getAllHeaders()[0].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[1].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(AUTHORIZATION_HEADER));

    GetConfigurationsRequest getConfigurationsRequest2 = new GetConfigurationsRequest.GetConfigurationsRequestBuilder(BASEURL, VERSION, TENANT_UUID).setConfigurationUuid(CONFIGURATION_UUID).setToken(TOKEN).build();

    assert(getConfigurationsRequest2.getTenantUuid().equals(TENANT_UUID));
    assert(getConfigurationsRequest2.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/" + CONFIGURATION_UUID));
    assert(getConfigurationsRequest2.getRequest().getMethod().equals("GET"));
    assert(getConfigurationsRequest2.getRequest().getURI().toString().equals(CONFIGURATION_REQUEST_URL));
    assert(getConfigurationsRequest2.getRequestUrl().equals(CONFIGURATION_REQUEST_URL));
    assert(getConfigurationsRequest2.getToken().equals(TOKEN));
  }

  @Test
  public void DeleteConfigurationRequestBuilderTest() {
    DeleteConfigurationRequest deleteConfigurationRequest = new DeleteConfigurationRequest.DeleteConfigurationsRequestBuilder(BASEURL, VERSION, TENANT_UUID).setToken(TOKEN).build();

    assert(deleteConfigurationRequest.getTenantUuid().equals(TENANT_UUID));
    assert(deleteConfigurationRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/"));
    assert(deleteConfigurationRequest.getRequest().getMethod().equals("DELETE"));
    assert(deleteConfigurationRequest.getRequest().getURI().toString().equals(BASE_REQUEST_URL));
    assert(deleteConfigurationRequest.getRequestUrl().equals(BASE_REQUEST_URL));
    assert(deleteConfigurationRequest.getToken().equals(TOKEN));

    HttpRequestBase requestBase = deleteConfigurationRequest.getRequest();

    assert(requestBase!=null);
    assert(requestBase.getAllHeaders()[0].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[1].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(AUTHORIZATION_HEADER));

    DeleteConfigurationRequest deleteConfigurationRequest2 = new DeleteConfigurationRequest.DeleteConfigurationsRequestBuilder(BASEURL, VERSION, TENANT_UUID).setConfigurationUuid(CONFIGURATION_UUID).setToken(TOKEN).build();

    assert(deleteConfigurationRequest2.getTenantUuid().equals(TENANT_UUID));
    assert(deleteConfigurationRequest2.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/" + CONFIGURATION_UUID));
    assert(deleteConfigurationRequest2.getRequest().getMethod().equals("DELETE"));
    assert(deleteConfigurationRequest2.getRequest().getURI().toString().equals(CONFIGURATION_REQUEST_URL));
    assert(deleteConfigurationRequest2.getRequestUrl().equals(CONFIGURATION_REQUEST_URL));
    assert(deleteConfigurationRequest2.getToken().equals(TOKEN));
  }


  @Test
  public void ConfigurationRequestBodyTest()
  {
    ConfigurationRequestBody configurationRequestBody = new ConfigurationRequestBody.ConfigurationRequestBodyBuilder()
        .setHost(HOST)
        .setMailFrom(MAIL_FROM)
        .setMailPassword(MAIL_PASSWORD)
        .setMailReturnPath(RETURN_PATH)
        .setMailUsername(MAIL_USERNAME)
        .setPort(PORT)
        .setProtocol(PROTOCOL)
        .setSmtpAuth(SMTP_AUTH)
        .setSmtpStarttlsEnable(SMTP_START_TLS_ENABLE)
        .build();

    assert(configurationRequestBody.toJson().equals(CONFIGURATION_REQUEST_BODY_JSON));
  }

}
