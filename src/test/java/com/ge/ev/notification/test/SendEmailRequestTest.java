package com.ge.ev.notification.test;

import static com.ge.ev.notification.test.Constants.BASEURL;
import static com.ge.ev.notification.test.Constants.CONFIGURATION_UUID;
import static com.ge.ev.notification.test.Constants.EMAIL;
import static com.ge.ev.notification.test.Constants.NAME;
import static com.ge.ev.notification.test.Constants.NOTIFICATION_REFERENCE_UUID;
import static com.ge.ev.notification.test.Constants.RECIPIENTS_JSON_ARRAY;
import static com.ge.ev.notification.test.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.test.Constants.TENANT_UUID;
import static com.ge.ev.notification.test.Constants.TOKEN;
import static com.ge.ev.notification.test.Constants.TYPE;
import static com.ge.ev.notification.test.Constants.VERSION;

import com.ge.ev.notification.client.requests.email.SendEmailRequest;
import com.ge.ev.notification.client.requests.email.SendEmailRequestBody;
import com.ge.ev.notification.client.requests.email.SendEmailRequestBodyRecipient;
import com.ge.ev.notification.client.requests.email.TemplateEmailRequestBody;
import com.ge.ev.notification.client.response.SendEmailResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SendEmailRequestTest {


  private static final String BODY ="This is a test email";
  private static final String SUBJECT = "This is a test subject";
  private static final String FROM_EMAIL = "test@notifications.ge.com";
  private static final String FROM_NAME = "test";
  private static final Boolean IMPORTANT = true;

  private static final String RECIPIENTS_JSON = "{\"body\":\"This is a test email\",\"recipients\":[{\"recipientName\":\"EV Notification Service\",\"email\":\"ev.notification@ge.com\",\"type\":\"to\"}],\"important\":true,\"subject\":\"This is a test subject\",\"fromEmail\":\"test@notifications.ge.com\",\"fromName\":\"test\"}";

  private static final String EMAIL_URI = "/tenants/"+TENANT_UUID+"/email?configuration=" + CONFIGURATION_UUID;
  private static final String EMAIL_URL = "https://notification.predix.io/v1/tenants/"+TENANT_UUID+"/email?configuration="+CONFIGURATION_UUID;

  private static final String TEMPLATE_EMAIL_URI = "/tenants/"+TENANT_UUID+"/email?configuration="+CONFIGURATION_UUID;
  private static final String TEMPLATE_EMAIL_URL = "https://notification.predix.io/v1/tenants/"+TENANT_UUID+"/email?configuration="+CONFIGURATION_UUID;

  private static final String SEND_EMAIL_REPONSE_JSON = "{\"errors\": [\"test error\"],\"notificationReferenceUuid\": \""+NOTIFICATION_REFERENCE_UUID+"\",\"notificationStatus\": \"NotificationEmailMessageQueued\"}";

  private static final String SEND_EMAIL_TEMPLATE_BODY_JSON = "{\"key1\":\"value1\",\"key2\":\"value2\"}";

  @Test
  public void TestSendEmailRequestBase() {

    SendEmailRequestBodyRecipient sendEmailRequestBodyRecipient = new SendEmailRequestBodyRecipient.SendEmailRequestBodyRecipientBuilder(NAME, EMAIL, TYPE).build();

    assert(sendEmailRequestBodyRecipient.getEmail().equals(EMAIL));
    assert(sendEmailRequestBodyRecipient.getRecipientName().equals(NAME));
    assert(sendEmailRequestBodyRecipient.getType().equals(TYPE));

    SendEmailRequestBody sendEmailRequestBody = new SendEmailRequestBody.SendEmailRequestBodyBuilder()
        .setBody(BODY)
        .setFromEmail(FROM_EMAIL)
        .setFromName(FROM_NAME)
        .setImportant(IMPORTANT)
        .addRecipients(sendEmailRequestBodyRecipient)
        .setSubject(SUBJECT)
        .build();

    assert(sendEmailRequestBody.getBody().equals(BODY));
    assert(sendEmailRequestBody.getFromEmail().equals(FROM_EMAIL));
    assert(sendEmailRequestBody.getFromName().equals(FROM_NAME));
    assert(sendEmailRequestBody.getImportant().equals(IMPORTANT));
    assert(sendEmailRequestBody.getSubject().equals(SUBJECT));

    SendEmailRequest sendEmailRequest = new SendEmailRequest.SendEmailRequestBuilder(BASEURL, VERSION, TENANT_UUID, CONFIGURATION_UUID)
        .setSendEmailRequestBody(sendEmailRequestBody)
        .setToken(TOKEN)
        .build();

    assert(sendEmailRequest.getTenantUuid().equals(TENANT_UUID));

    assert(sendEmailRequest.getRequestUri().equals(EMAIL_URI));
    assert(sendEmailRequest.getRequest().getMethod().equals("POST"));
    assert(sendEmailRequest.getRequest().getURI().toString().equals(EMAIL_URL));
    assert(sendEmailRequest.getRequestUrl().equals(EMAIL_URL));
    assert(sendEmailRequest.getToken().equals(TOKEN));
    assert(sendEmailRequest.getConfigurationUuid().equals(CONFIGURATION_UUID));
    assert(sendEmailRequest.getSendEmailRequestBody().toJson().equals(RECIPIENTS_JSON));


    SendEmailRequest sendEmailRequest2 = new SendEmailRequest.SendEmailRequestBuilder(BASEURL, VERSION, TENANT_UUID,CONFIGURATION_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setSendEmailRequestBody(sendEmailRequestBody)
        .setToken(TOKEN)
        .build();

    assert(sendEmailRequest2.getTenantUuid().equals(TENANT_UUID));
    assert(sendEmailRequest.getRequestUri().equals(TEMPLATE_EMAIL_URI));
    assert(sendEmailRequest.getRequest().getMethod().equals("POST"));
    assert(sendEmailRequest.getRequest().getURI().toString().equals(TEMPLATE_EMAIL_URL));
    assert(sendEmailRequest.getRequestUrl().equals(TEMPLATE_EMAIL_URL));
    assert(sendEmailRequest2.getToken().equals(TOKEN));
    assert(sendEmailRequest2.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(sendEmailRequest2.getConfigurationUuid().equals(CONFIGURATION_UUID));
    assert(sendEmailRequest2.getSendEmailRequestBody().toJson().equals(RECIPIENTS_JSON));
    
  }

  @Test
  public void TestSendEmailRequestBodyBase() {

    SendEmailRequestBodyRecipient sendEmailRequestBodyRecipient = new SendEmailRequestBodyRecipient.SendEmailRequestBodyRecipientBuilder(NAME, EMAIL, TYPE).build();
    assert(sendEmailRequestBodyRecipient.getEmail().equals(EMAIL));
    assert(sendEmailRequestBodyRecipient.getRecipientName().equals(NAME));
    assert(sendEmailRequestBodyRecipient.getType().equals(TYPE));

    SendEmailRequestBodyRecipient sendEmailRequestBodyRecipient2 = new SendEmailRequestBodyRecipient.SendEmailRequestBodyRecipientBuilder(NAME, EMAIL, TYPE).build();
    assert(sendEmailRequestBodyRecipient2.getEmail().equals(EMAIL));
    assert(sendEmailRequestBodyRecipient2.getRecipientName().equals(NAME));
    assert(sendEmailRequestBodyRecipient2.getType().equals(TYPE));

    SendEmailRequestBody sendEmailRequestBody = new SendEmailRequestBody.SendEmailRequestBodyBuilder()
        .setBody(BODY)
        .setFromEmail(FROM_EMAIL)
        .setFromName(FROM_NAME)
        .setImportant(IMPORTANT)
        .addRecipients(sendEmailRequestBodyRecipient)
        .addRecipients(sendEmailRequestBodyRecipient2)
        .setSubject(SUBJECT)
        .build();

    assert(sendEmailRequestBody.getBody().equals(BODY));
    assert(sendEmailRequestBody.getFromEmail().equals(FROM_EMAIL));
    assert(sendEmailRequestBody.getFromName().equals(FROM_NAME));
    assert(sendEmailRequestBody.getImportant().equals(IMPORTANT));
    assert(sendEmailRequestBody.getSubject().equals(SUBJECT));
    assert(sendEmailRequestBody.getRecipients().toJson().equals(RECIPIENTS_JSON_ARRAY));
  }

  @Test
  public void TestSendEmailRequestBodyRecipientBase() {
    SendEmailRequestBodyRecipient sendEmailRequestBodyRecipient = new SendEmailRequestBodyRecipient.SendEmailRequestBodyRecipientBuilder(NAME, EMAIL, TYPE).build();

    assert(sendEmailRequestBodyRecipient.getEmail().equals(EMAIL));
    assert(sendEmailRequestBodyRecipient.getRecipientName().equals(NAME));
    assert(sendEmailRequestBodyRecipient.getType().equals(TYPE));
  }

  @Test
  public void TestSendEmailResponse()
  {
    SendEmailResponse sendEmailResponse =  SendEmailResponse.toObject(SEND_EMAIL_REPONSE_JSON);
    assert(sendEmailResponse.getErrors().get(0).equals("test error"));
    assert(sendEmailResponse.getNotificationReferenceUuid().equals(NOTIFICATION_REFERENCE_UUID));
    assert(sendEmailResponse.getNotificationStatus().equals("NotificationEmailMessageQueued"));
  }

  @Test
  public void TestTemplateEmailRequestBody()
  {
    SendEmailResponse sendEmailResponse =  SendEmailResponse.toObject(SEND_EMAIL_REPONSE_JSON);

    TemplateEmailRequestBody templateEmailRequestBody = new TemplateEmailRequestBody.TemplateEmailRequestBodyBuilder()
        .addKeyValue("key1", "value1")
        .addKeyValue("key2", "value2")
        .build();

    assert(templateEmailRequestBody.toJson().equals(SEND_EMAIL_TEMPLATE_BODY_JSON));

  }

}
