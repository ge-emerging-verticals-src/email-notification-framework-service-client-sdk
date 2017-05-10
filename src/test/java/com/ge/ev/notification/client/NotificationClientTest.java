package com.ge.ev.notification.client;

import static com.ge.ev.notification.Constants.BASEURL;
import static com.ge.ev.notification.Constants.EMAIL;
import static com.ge.ev.notification.Constants.MATCHER_JSON;
import static com.ge.ev.notification.Constants.RECIPIENT_RESPONSE_JSON;
import static com.ge.ev.notification.Constants.TEMPLATE_DESCRIPTION;
import static com.ge.ev.notification.Constants.TEMPLATE_DOMAIN;
import static com.ge.ev.notification.Constants.TEMPLATE_FILE;
import static com.ge.ev.notification.Constants.TEMPLATE_JSON;
import static com.ge.ev.notification.Constants.TEMPLATE_NAME;
import static com.ge.ev.notification.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.Constants.TENANT_UUID;
import static com.ge.ev.notification.Constants.TOKEN;
import static com.ge.ev.notification.Constants.VERSION;

import com.ge.ev.notification.client.NotificationServiceClient.NotificationServiceClientBuilder;
import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.domain.Matcher;
import com.ge.ev.notification.client.domain.NotificationEvent;
import com.ge.ev.notification.client.domain.Recipient;
import com.ge.ev.notification.client.domain.Template;
import com.ge.ev.notification.client.domain.Tenant;
import com.ge.ev.notification.client.exceptions.NotificationClientException;
import com.ge.ev.notification.client.exceptions.RequestException;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.configuration.CreateConfigurationRequestBody;
import com.ge.ev.notification.client.requests.configuration.UpdateConfigurationRequestBody;
import com.ge.ev.notification.client.requests.email.RecipientType;
import com.ge.ev.notification.client.requests.email.SendEmailRequestBody;
import com.ge.ev.notification.client.requests.email.SendEmailRequestBodyRecipient;
import com.ge.ev.notification.client.requests.email.SendTemplateEmailRequestBody;
import com.ge.ev.notification.client.requests.template.MatchersRequestBody;
import com.ge.ev.notification.client.requests.template.CreateRecipientsRequestBody;
import com.ge.ev.notification.client.requests.template.TemplateRequestBody;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
import com.ge.ev.notification.client.response.SendEmailResponse;
import com.ge.ev.notification.vcap.domain.NotificationServiceEnvironmentElement;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotificationClientTest {

  @Test
  public void TestNotificationClientBase()
  {

    HashMap<String, Object> map = new HashMap<>();
    String[] tags = new String[1];
    tags[0] = "notification";
    map.put("label", "notification");
    map.put("plan", "Beta");
    map.put("name", "notification-test");
    map.put("tags", tags);

    HashMap<String, Object> credentials = new HashMap<>();
    credentials.put("zoneOauthScope","notification.zone.bfb2c110-50cc-41b9-ac3a-198c7598b4b5.user");
    credentials.put("trustedIssuerIds","https://www.predix.io/oauth/token");
    credentials.put("version","v1");
    credentials.put("tenantUuid","2e04e462-7df2-4a15-98ec-f707cb98963c");
    credentials.put("catalogUri","https://ev-notification-service.run.aws-usw02-pr.ice.predix.io");
    map.put("credentials", credentials);

    NotificationServiceEnvironmentElement notificationServiceEnvironmentElement = NotificationServiceEnvironmentElement.toObject(map);

    NotificationServiceClient notificationServiceClient = new NotificationServiceClientBuilder(notificationServiceEnvironmentElement)
        .build();
    
    assert(notificationServiceClient.getBaseUrl().equals("https://ev-notification-service.run.aws-usw02-pr.ice.predix.io"));
    assert(notificationServiceClient.getVersion().equals("v1"));
    assert(notificationServiceClient.getTenantUuid().equals("2e04e462-7df2-4a15-98ec-f707cb98963c"));
  }

  @Test
  public void TestNotificationClientServiceCalls()
  {
    NotificationServiceClient notificationServiceClient = new NotificationServiceClientBuilder(null)
        .setBaseUrl(BASEURL)
        .setVersion(VERSION)
        .setTenantUuid(TENANT_UUID)
        .build();

    Tenant tenant = null;
    try {
      tenant = notificationServiceClient.getTenant(TOKEN);
    } catch (RequestException e) {
     assert(tenant == null);
    } catch (NotificationClientException e) {
      assert(tenant == null);

    }

    UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody = new UpdateTenantConfigurationRequestBody.UpdateTenantConfigurationRequestBodyBuilder()
        .setFailWebhook("https://notification-test-webhook.predix.io/fail")
        .setSuccessWebhook("https://notification-test-webhook.predix.io/success")
        .build();

    try {
      tenant = notificationServiceClient.updateTenant(TOKEN, updateTenantConfigurationRequestBody);
    } catch (RequestException e) {
      assert(tenant == null);
    } catch (NotificationClientException e) {
      assert(tenant == null);
    }

    List<Configuration> configurations = null;
    try {
      configurations =  notificationServiceClient.getConfigurations(TOKEN);
    } catch (RequestException e) {
      assert (configurations==null);
    } catch (NotificationClientException e) {
      assert (configurations==null);
    }

    Configuration newConfiguration = new Configuration.ConfigurationBuilder()
        .setHost("smtp.test.notification.com")
        .setMailFrom("ev.notification.test@test.notification.com")
        .setMailPassword("f70c16c7-f157-41cf-ad2e-be53b2864b1a")
        .setMailUsername("e5ff5261-70b8-40b7-ba62-2512abcf5c38")
        .setPort(587)
        .setProtocol("smtp")
        .setSmtpAuth(true)
        .setSmtpStarttlsEnable(true)
        .build();

    CreateConfigurationRequestBody configurationRequestBody = new CreateConfigurationRequestBody.CreateConfigurationRequestBodyBuilder()
        .addConfigurations(newConfiguration)
        .build();

    Configuration returnConfiguration = null;
    try {
      returnConfiguration =  notificationServiceClient.createConfiguration(TOKEN, configurationRequestBody);
    } catch (RequestException e) {
     assert (returnConfiguration == null);
    } catch (NotificationClientException e) {
      assert (returnConfiguration == null);
    }

    Configuration updateConfiguration = new Configuration.ConfigurationBuilder()
        .setHost("smtp.test.notification.com")
        .setMailFrom("ev.notification.test@test.notification.com")
            .setMailPassword("f70c16c7-f157-41cf-ad2e-be53b2864b1a")
            .setMailUsername("e5ff5261-70b8-40b7-ba62-2512abcf5c38")
            .setPort(587)
            .setProtocol("smtp")
            .setSmtpAuth(true)
            .setSmtpStarttlsEnable(true)
            .build();

    UpdateConfigurationRequestBody updateConfigurationRequestBody = new UpdateConfigurationRequestBody.UpdateConfigurationRequestBodyBuilder()
        .setConfiguration(updateConfiguration)
        .build();

    try {
      returnConfiguration = notificationServiceClient.updateConfiguration(TOKEN,  null,  updateConfigurationRequestBody);
    } catch (RequestException e) {
      assert (returnConfiguration == null);
    } catch (NotificationClientException e) {
      assert (returnConfiguration == null);
    }

    try {
      returnConfiguration = notificationServiceClient.updateConfiguration(TOKEN,  newConfiguration,  null);
    } catch (RequestException e) {
      assert (returnConfiguration == null);
    } catch (NotificationClientException e) {
      assert (returnConfiguration == null);
    }

    try {
      returnConfiguration = notificationServiceClient.updateConfiguration(TOKEN,  newConfiguration,  updateConfigurationRequestBody);
    } catch (RequestException e) {
      assert (returnConfiguration == null);
    } catch (NotificationClientException e) {
      assert (returnConfiguration == null);
    }

    try {
      returnConfiguration = notificationServiceClient.deleteConfiguration(TOKEN, null);
    } catch (RequestException e) {
      assert (returnConfiguration == null);
    } catch (NotificationClientException e) {
      assert (returnConfiguration == null);
    }

    try {
      returnConfiguration = notificationServiceClient.deleteConfiguration(TOKEN, newConfiguration);
    } catch (RequestException e) {
      assert (returnConfiguration == null);
    } catch (NotificationClientException e) {
      assert (returnConfiguration == null);
    }

    SendEmailRequestBodyRecipient sendEmailRequestBodyRecipient = new SendEmailRequestBodyRecipient.SendEmailRequestBodyRecipientBuilder(EMAIL, EMAIL, RecipientType.to).build();

    SendEmailRequestBody sendEmailRequestBody = new SendEmailRequestBody.SendEmailRequestBodyBuilder()
        .setBody("TEST BODY")
        .setFromEmail("ev.notification.test@test.notification.com")
        .setFromName("TEST FROM")
        .setImportant(true)
        .addRecipients(sendEmailRequestBodyRecipient)
        .setSubject("TEST SUBJECT")
        .build();

    SendEmailResponse sendEmailResponse = null;
    try {
      sendEmailResponse = notificationServiceClient.sendEmail(TOKEN, null, sendEmailRequestBody);
    } catch (RequestException e) {
      assert (sendEmailResponse == null);
    } catch (NotificationClientException e) {
      assert (sendEmailResponse == null);
    }

    try {
      sendEmailResponse = notificationServiceClient.sendEmail(TOKEN, newConfiguration, null);
    } catch (RequestException e) {
      assert (sendEmailResponse == null);
    } catch (NotificationClientException e) {
      assert (sendEmailResponse == null);
    }

    try {
      sendEmailResponse = notificationServiceClient.sendEmail(TOKEN, newConfiguration, sendEmailRequestBody);
    } catch (RequestException e) {
      assert (sendEmailResponse == null);
    } catch (NotificationClientException e) {
      assert (sendEmailResponse == null);
    }

    List<Template> templates = null;
    try {
      templates = notificationServiceClient.getTemplates(TOKEN);
    } catch (RequestException e) {
      assert(templates == null);
    } catch (NotificationClientException e) {
      assert(templates == null);
    }

    InputStream in = null;
    in = new ByteArrayInputStream(TEMPLATE_FILE.getBytes());

    TemplateRequestBody templateRequestBody = new TemplateRequestBody.TemplateRequestBodyBuilder(TEMPLATE_DOMAIN, TEMPLATE_NAME, TEMPLATE_DESCRIPTION )
        .setSubjectTemplate("sdk test subject")
        .build();

    Template template = null;
    try {
      template= notificationServiceClient.createTemplate(TOKEN, templateRequestBody, in);
    } catch (RequestException e) {
     assert(template == null);
    } catch (NotificationClientException e) {
      assert(template == null);
    }

    Template testTemplate = JsonObject.toObject(TEMPLATE_JSON, Template.class);

    SendTemplateEmailRequestBody sendTemplateEmailRequestBody = new SendTemplateEmailRequestBody.SendTemplateEmailRequestBodyBuilder()
        .addKeyValue("api1_count", 11230)
        .addKeyValue("api2_count", 23540)
        .addKeyValue("api3_count", 56215)
        .addKeyValue("type", "daily")
        .build();

    try {
      sendEmailResponse =  notificationServiceClient.sendTemplateEmail(TOKEN, null, testTemplate, sendTemplateEmailRequestBody);
    } catch (RequestException e) {
      assert (sendEmailResponse == null);
    } catch (NotificationClientException e) {
      assert (sendEmailResponse == null);
    }

    try {
      sendEmailResponse =  notificationServiceClient.sendTemplateEmail(TOKEN, newConfiguration, null, sendTemplateEmailRequestBody);
    } catch (RequestException e) {
      assert (sendEmailResponse == null);
    } catch (NotificationClientException e) {
      assert (sendEmailResponse == null);
    }

    try {
      sendEmailResponse =  notificationServiceClient.sendTemplateEmail(TOKEN, newConfiguration, testTemplate, null);
    } catch (RequestException e) {
      assert (sendEmailResponse == null);
    } catch (NotificationClientException e) {
      assert (sendEmailResponse == null);
    }

    try {
     sendEmailResponse =  notificationServiceClient.sendTemplateEmail(TOKEN, newConfiguration, testTemplate, sendTemplateEmailRequestBody);
    } catch (RequestException e) {
      assert (sendEmailResponse == null);
    } catch (NotificationClientException e) {
      assert (sendEmailResponse == null);
    }

    List<NotificationEvent> events = null;
    try {
      events = notificationServiceClient.getEvents(TOKEN, TEMPLATE_UUID);
    } catch (RequestException e) {
     assert (events == null);
    } catch (NotificationClientException e) {
      assert (events == null);
    }

    Template deleteTemplate = null;

    try {
      deleteTemplate = notificationServiceClient.deleteTemplate(TOKEN, null);
    } catch (RequestException e) {
      assert(deleteTemplate == null);
    } catch (NotificationClientException e) {
      assert(deleteTemplate == null);
    }

    try {
      deleteTemplate = notificationServiceClient.deleteTemplate(TOKEN, testTemplate);
    } catch (RequestException e) {
      assert(deleteTemplate == null);
    } catch (NotificationClientException e) {
      assert(deleteTemplate == null);
    }

    List<Matcher> matchers = null;

    try {
      matchers = notificationServiceClient.getMatchers(TOKEN, null);
    } catch (RequestException e) {
      assert(matchers == null);
    } catch (NotificationClientException e) {
      assert(matchers == null);
    }

    try {
      matchers = notificationServiceClient.getMatchers(TOKEN, testTemplate);
    } catch (RequestException e) {
      assert(matchers == null);
    } catch (NotificationClientException e) {
      assert(matchers == null);
    }

    MatchersRequestBody matchersRequestBody = new MatchersRequestBody.MatchersRequestBodyBuilder("$.[?(@.type in ['daily','alert'])]").build();

    Matcher matcher = null;

    try {
      matcher = notificationServiceClient.createMatcher(TOKEN,  null, matchersRequestBody);
    } catch (RequestException e) {
      assert(matcher == null);
    } catch (NotificationClientException e) {
      assert(matcher == null);
    }

    try {
      matcher = notificationServiceClient.createMatcher(TOKEN,  testTemplate, null);
    } catch (RequestException e) {
      assert(matcher == null);
    } catch (NotificationClientException e) {
      assert(matcher == null);
    }

    try {
      matcher = notificationServiceClient.createMatcher(TOKEN,  testTemplate, matchersRequestBody);
    } catch (RequestException e) {
      assert(matcher == null);
    } catch (NotificationClientException e) {
      assert(matcher == null);
    }


    Matcher testMatcher = JsonObject.toObject(MATCHER_JSON, Matcher.class);
    Matcher deleteMatcher = null;
    try {
      deleteMatcher = notificationServiceClient.deleteMatcher(TOKEN,  null,  testMatcher);
    } catch (RequestException e) {
      assert(deleteMatcher == null);
    } catch (NotificationClientException e) {
      assert(deleteMatcher == null);
    }

    try {
      deleteMatcher = notificationServiceClient.deleteMatcher(TOKEN,  testTemplate,  null);
    } catch (RequestException e) {
      assert(deleteMatcher == null);
    } catch (NotificationClientException e) {
      assert(deleteMatcher == null);
    }


    try {
      deleteMatcher = notificationServiceClient.deleteMatcher(TOKEN,  testTemplate,  testMatcher);
    } catch (RequestException e) {
      assert(deleteMatcher == null);
    } catch (NotificationClientException e) {
      assert(deleteMatcher == null);
    }

    List<Recipient> recipients = null;
    try {
      recipients = notificationServiceClient.getRecipients(TOKEN,  null,  testMatcher );
    } catch (RequestException e) {
      assert(recipients == null);
    } catch (NotificationClientException e) {
      assert(recipients == null);
    }

    try {
      recipients = notificationServiceClient.getRecipients(TOKEN,  testTemplate,  null );
    } catch (RequestException e) {
      assert(recipients == null);
    } catch (NotificationClientException e) {
      assert(recipients == null);
    }

    try {
      recipients = notificationServiceClient.getRecipients(TOKEN,  testTemplate,  testMatcher );
    } catch (RequestException e) {
      assert(recipients == null);
    } catch (NotificationClientException e) {
      assert(recipients == null);
    }

    CreateRecipientsRequestBody createRecipientsRequestBody = new CreateRecipientsRequestBody.CreateRecipientsRequestBodyBuilder().addRecipient("dat.nguyen@ge.com").build();

    try {
      recipients =  notificationServiceClient.createRecipients(TOKEN,  null,  testMatcher,  createRecipientsRequestBody );
    } catch (RequestException e) {
      assert(recipients == null);
    } catch (NotificationClientException e) {
      assert(recipients == null);
    }
    try {
      recipients =  notificationServiceClient.createRecipients(TOKEN,  testTemplate,  null,  createRecipientsRequestBody );
    } catch (RequestException e) {
      assert(recipients == null);
    } catch (NotificationClientException e) {
      assert(recipients == null);
    }

    try {
      recipients =  notificationServiceClient.createRecipients(TOKEN,  testTemplate,  testMatcher,  null );
    } catch (RequestException e) {
      assert(recipients == null);
    } catch (NotificationClientException e) {
      assert(recipients == null);
    }

    try {
      recipients =  notificationServiceClient.createRecipients(TOKEN,  testTemplate,  testMatcher,  createRecipientsRequestBody );
    } catch (RequestException e) {
      assert(recipients == null);
    } catch (NotificationClientException e) {
      assert(recipients == null);
    }

    Recipient recipient = null;
    Recipient deleteRecipient = JsonObject.toObject(RECIPIENT_RESPONSE_JSON, Recipient.class);
    try {
      recipient = notificationServiceClient.deleteRecipient(TOKEN,   null,  testMatcher, deleteRecipient );
    } catch (RequestException e) {
      assert(recipient == null);
    } catch (NotificationClientException e) {
      assert(recipient == null);
    }
    try {
      recipient = notificationServiceClient.deleteRecipient(TOKEN,   testTemplate,  null, deleteRecipient );
    } catch (RequestException e) {
      assert(recipient == null);
    } catch (NotificationClientException e) {
      assert(recipient == null);
    }
    try {
      recipient = notificationServiceClient.deleteRecipient(TOKEN,   testTemplate,  testMatcher, null );
    } catch (RequestException e) {
      assert(recipient == null);
    } catch (NotificationClientException e) {
      assert(recipient == null);
    }
    try {
      recipient = notificationServiceClient.deleteRecipient(TOKEN,   testTemplate,  testMatcher, deleteRecipient );
    } catch (RequestException e) {
      assert(recipient == null);
    } catch (NotificationClientException e) {
      assert(recipient == null);
    }


  }


}
