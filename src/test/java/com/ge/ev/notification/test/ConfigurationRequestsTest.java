package com.ge.ev.notification.test;

import static com.ge.ev.notification.test.Constants.AUTHORIZATION_HEADER;
import static com.ge.ev.notification.test.Constants.BASEURL;
import static com.ge.ev.notification.test.Constants.CONFIGURATION_UUID;
import static com.ge.ev.notification.test.Constants.CONTENT_HEADER;
import static com.ge.ev.notification.test.Constants.TENANT_UUID;
import static com.ge.ev.notification.test.Constants.TOKEN;
import static com.ge.ev.notification.test.Constants.VERSION;

import com.ge.ev.notification.client.requests.configuration.ConfigurationRequestBody;
import com.ge.ev.notification.client.requests.configuration.CreateConfigurationRequest;
import com.ge.ev.notification.client.requests.configuration.DeleteConfigurationRequest;
import com.ge.ev.notification.client.requests.configuration.GetConfigurationsRequest;
import com.ge.ev.notification.client.requests.configuration.UpdateConfigurationRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/1/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationRequestsTest {


  public static final String BASE_REQUEST_URL = BASEURL + "/"  + VERSION + "/tenants/" + TENANT_UUID + "/configurations/";
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
  private static final String CONFIGURATION_REQUEST_BODY_ARRAY_JSON = "[{\"protocol\":\"smtp\",\"host\":\"smtp.mail.notification.ge.com\",\"port\":587,\"smtpAuth\":true,\"smtpStarttlsEnable\":true,\"mailFrom\":\"test@notification.ge.com\",\"mailUsername\":\"test\",\"mailPassword\":\"test123\",\"mailReturnPath\":\"return.path\"}]";

  @Test
  public void TestGetConfigurationRequestBuilder() {
    GetConfigurationsRequest getConfigurationsRequest = new GetConfigurationsRequest.GetConfigurationsRequestBuilder(BASEURL, VERSION, TENANT_UUID).setToken(TOKEN).build();

    assert(getConfigurationsRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getConfigurationsRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/"));
    assert(getConfigurationsRequest.getRequest().getMethod().equals("GET"));
    assert(getConfigurationsRequest.getRequest().getURI().toString().equals(BASE_REQUEST_URL));
    assert(getConfigurationsRequest.getRequestUrl().equals(BASE_REQUEST_URL));
    assert(getConfigurationsRequest.getToken().equals(TOKEN));

    HttpRequestBase requestBase = getConfigurationsRequest.getRequest();

    assert(requestBase!=null);
    assert(requestBase.getAllHeaders()[1].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[0].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(AUTHORIZATION_HEADER));

    GetConfigurationsRequest getConfigurationsRequest2 = new GetConfigurationsRequest.GetConfigurationsRequestBuilder(BASEURL, VERSION, TENANT_UUID).setConfigurationUuid(CONFIGURATION_UUID).setToken(TOKEN).build();

    assert(getConfigurationsRequest2.getTenantUuid().equals(TENANT_UUID));
    assert(getConfigurationsRequest2.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/" + CONFIGURATION_UUID));
    assert(getConfigurationsRequest2.getRequest().getMethod().equals("GET"));
    assert(getConfigurationsRequest2.getRequest().getURI().toString().equals(CONFIGURATION_REQUEST_URL));
    assert(getConfigurationsRequest2.getRequestUrl().equals(CONFIGURATION_REQUEST_URL));
    assert(getConfigurationsRequest2.getToken().equals(TOKEN));
  }

  @Test
  public void TestDeleteConfigurationRequestBuilder() {
    DeleteConfigurationRequest deleteConfigurationRequest = new DeleteConfigurationRequest.DeleteConfigurationsRequestBuilder(BASEURL, VERSION, TENANT_UUID).setToken(TOKEN).build();

    assert(deleteConfigurationRequest.getTenantUuid().equals(TENANT_UUID));
    assert(deleteConfigurationRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/"));
    assert(deleteConfigurationRequest.getRequest().getMethod().equals("DELETE"));
    assert(deleteConfigurationRequest.getRequest().getURI().toString().equals(BASE_REQUEST_URL));
    assert(deleteConfigurationRequest.getRequestUrl().equals(BASE_REQUEST_URL));
    assert(deleteConfigurationRequest.getToken().equals(TOKEN));

    HttpRequestBase requestBase = deleteConfigurationRequest.getRequest();

    assert(requestBase!=null);
    assert(requestBase.getAllHeaders()[1].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[0].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(AUTHORIZATION_HEADER));

    DeleteConfigurationRequest deleteConfigurationRequest2 = new DeleteConfigurationRequest.DeleteConfigurationsRequestBuilder(BASEURL, VERSION, TENANT_UUID).setConfigurationUuid(CONFIGURATION_UUID).setToken(TOKEN).build();

    assert(deleteConfigurationRequest2.getTenantUuid().equals(TENANT_UUID));
    assert(deleteConfigurationRequest2.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/" + CONFIGURATION_UUID));
    assert(deleteConfigurationRequest2.getRequest().getMethod().equals("DELETE"));
    assert(deleteConfigurationRequest2.getRequest().getURI().toString().equals(CONFIGURATION_REQUEST_URL));
    assert(deleteConfigurationRequest2.getRequestUrl().equals(CONFIGURATION_REQUEST_URL));
    assert(deleteConfigurationRequest2.getToken().equals(TOKEN));
  }

  @Test
  public void TestCreateConfigurationRequestBuilder() {
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

    CreateConfigurationRequest createConfigurationRequest = new CreateConfigurationRequest.CreateConfigurationRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .addConfigurationRequestBody(configurationRequestBody)
        .setToken(TOKEN)
        .build();

    assert(createConfigurationRequest.getTenantUuid().equals(TENANT_UUID));
    assert(createConfigurationRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/"));
    assert(createConfigurationRequest.getRequest().getMethod().equals("POST"));
    assert(createConfigurationRequest.getRequest().getURI().toString().equals(BASE_REQUEST_URL));
    assert(createConfigurationRequest.getRequestUrl().equals(BASE_REQUEST_URL));
    assert(createConfigurationRequest.getToken().equals(TOKEN));

    assert(createConfigurationRequest.getConfigurationRequestBodies().toJson().equals(CONFIGURATION_REQUEST_BODY_ARRAY_JSON));
    assert(createConfigurationRequest.getConfigurationRequestBodies().get(0).toJson().equals(CONFIGURATION_REQUEST_BODY_JSON));

    HttpRequestBase requestBase = createConfigurationRequest.getRequest();

    assert(requestBase!=null);
    assert(requestBase.getAllHeaders()[1].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[0].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(AUTHORIZATION_HEADER));
    
  }

  @Test
  public void TestUpdateConfigurationRequestBuilder() {
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

    UpdateConfigurationRequest updateConfigurationRequest = new UpdateConfigurationRequest.UpdateConfigurationRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setConfigurationRequestBody(configurationRequestBody)
        .setToken(TOKEN)
        .build();

    assert(updateConfigurationRequest.getTenantUuid().equals(TENANT_UUID));
    assert(updateConfigurationRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/configurations/"));
    assert(updateConfigurationRequest.getRequest().getMethod().equals("PUT"));
    assert(updateConfigurationRequest.getRequest().getURI().toString().equals(BASE_REQUEST_URL));
    assert(updateConfigurationRequest.getRequestUrl().equals(BASE_REQUEST_URL));
    assert(updateConfigurationRequest.getToken().equals(TOKEN));
    assert(updateConfigurationRequest.getRequestBody().toJson().equals(CONFIGURATION_REQUEST_BODY_JSON));

    HttpRequestBase requestBase = updateConfigurationRequest.getRequest();

    assert(requestBase!=null);
    assert(requestBase.getAllHeaders()[1].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[0].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(AUTHORIZATION_HEADER));
  }

  @Test
  public void TestConfigurationRequestBody()
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
