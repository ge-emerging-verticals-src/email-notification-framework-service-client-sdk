package com.ge.ev.notification;

import com.ge.ev.notification.client.requests.email.RecipientType;

/**
 * Created by 212391398 on 5/3/17.
 */
public class Constants {
  public static final String BASEURL = "https://notification.predix.io";
  public static final String VERSION = "v1";
  public static final String TENANT_UUID = "2beb4e8d-7eba-4cc1-b584-3d95bf84aedc";
  public static final String TOKEN = "8536aed6-0422-4e53-bb7d-4fc967128c50";
  public static final String CONFIGURATION_UUID = "cd407863-e152-4b3f-9635-ce153311a12c";
  public static final String CONTENT_HEADER = "application/json";
  public static final String AUTHORIZATION_HEADER = "bearer " + TOKEN;
  public static final String TEST_UUID = "0959f9de-74a7-48b4-8f84-34ef67b1b5df";
  public static final String TEST_MESSAGE = "NotificationEmailMessageQueued";
  public static final String TEST_ERROR = "test error";
  public static final String SAMPLE_JSON = "{\"notificationStatus\":\"NotificationEmailMessageQueued\",\"errors\":[\"test error\"],\"notificationReferenceUuid\":\"0959f9de-74a7-48b4-8f84-34ef67b1b5df\"}";
  public static final String TEST_DETAILS = "test details";
  public static final Integer STATUS = 1000;
  public static final String TEST_VARIABLES = "test variables";
  public static final String NOTIFICATION_REFERENCE_UUID = "fae02293-e366-4e73-82ff-49699faae735";
  public static final String RECIPIENTS_JSON_ARRAY = "[{\"recipientName\":\"EV Notification Service\",\"email\":\"ev.notification@ge.com\",\"type\":\"to\"},{\"recipientName\":\"EV Notification Service\",\"email\":\"ev.notification@ge.com\",\"type\":\"to\"}]";
  public static final String EMAIL ="ev.notification@ge.com";
  public static final String NAME = "EV Notification Service";
  public static final RecipientType TYPE = RecipientType.to;
  public static final String TEMPLATE_UUID = "2f9f0c1a-2ca5-4a86-a366-4b9e6db7253c";
  public static final String MATCHER_UUID = "3436eaef-ebd9-4b83-b673-4696f51684ea";
  public static final String RECIPIENT_UUID = "88355756-634f-4fc2-bab9-9e98d067fa34";


  public static final String TEMPLATE_DOMAIN = "notification.ge.com";
  public static final String TEMPLATE_NAME = "test_template";
  public static final String TEMPLATE_DESCRIPTION = "test template description";
  public static final String TEMPLATE_SUBJECT = "test subject";

  public static final String MATCHER = "$.[?(@.type in ['daily','alert'])]";

  public static final String SEND_EMAIL_REPONSE_JSON = "{\"errors\": [\"test error\"],\"notificationReferenceUuid\": \""+NOTIFICATION_REFERENCE_UUID+"\",\"notificationStatus\": \"NotificationEmailMessageQueued\"}";

  public static final String VCAP_SERVICE_JSON = "{\n"
      + "\t\"predix-uaa\": [{\n"
      + "\t\t\"name\": \"poc-uaa\",\n"
      + "\t\t\"label\": \"predix-uaa\",\n"
      + "\t\t\"tags\": [],\n"
      + "\t\t\"plan\": \"Free\",\n"
      + "\t\t\"credentials\": {\n"
      + "\t\t\t\"issuerId\": \"https://ef0438ec-7224-4695-8130-bdc4b2622c68.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token\",\n"
      + "\t\t\t\"subdomain\": \"ef0438ec-7224-4695-8130-bdc4b2622c68\",\n"
      + "\t\t\t\"dashboardUrl\": \"https://uaa-dashboard.run.aws-usw02-pr.ice.predix.io/#/login/ef0438ec-7224-4695-8130-bdc4b2622c68\",\n"
      + "\t\t\t\"zone\": {\n"
      + "\t\t\t\t\"http-header-value\": \"ef0438ec-7224-4695-8130-bdc4b2622c68\",\n"
      + "\t\t\t\t\"http-header-name\": \"X-Identity-Zone-Id\"\n"
      + "\t\t\t},\n"
      + "\t\t\t\"uri\": \"https://ef0438ec-7224-4695-8130-bdc4b2622c68.predix-uaa.run.aws-usw02-pr.ice.predix.io\"\n"
      + "\t\t}\n"
      + "\t}],\n"
      + "\t\"notification\": [{\n"
      + "\t\t\"name\": \"notification-hms\",\n"
      + "\t\t\"label\": \"notification\",\n"
      + "\t\t\"tags\": [\"notification\", \"email\", \"audit\", \"event\", \"trail\"],\n"
      + "\t\t\"plan\": \"Beta\",\n"
      + "\t\t\"credentials\": {\n"
      + "\t\t\t\"zoneOauthScope\": \"notification.zone.244a6f32-b781-48c2-82a4-35f6b6d0feab.user\",\n"
      + "\t\t\t\"trustedIssuerIds\": \"https://a9e8f016-ff01-43f7-a109-21d30b272fbd.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token\",\n"
      + "\t\t\t\"version\": \"\",\n"
      + "\t\t\t\"tenantUuid\": \"244a6f32-b781-48c2-82a4-35f6b6d0feab\",\n"
      + "\t\t\t\"catalogUri\": \"https://ev-notification-service.run.aws-usw02-pr.ice.predix.io\"\n"
      + "\t\t}\n"
      + "\t}]\n"
      + "}";

  public static final String VCAP_NOTIFICATION_SERVICE = "{\n"
      + "\t\"tags\": [\"notification\"],\n"
      + "\t\"plan\": \"Beta\",\n"
      + "\t\"name\": \"notification-test\",\n"
      + "\t\"label\": \"notification\",\n"
      + "\t\"credentials\": {\n"
      + "\t\t\"zoneOauthScope\": \"notification.zone.bfb2c110-50cc-41b9-ac3a-198c7598b4b5.user\",\n"
      + "\t\t\"trustedIssuerIds\": \"https://www.predix.io/oauth/token\",\n"
      + "\t\t\"version\": \"v1\",\n"
      + "\t\t\"tenantUuid\": \"2e04e462-7df2-4a15-98ec-f707cb98963c\",\n"
      + "\t\t\"catalogUri\": \"https://ev-notification-service.run.aws-usw02-pr.ice.predix.io\"\n"
      + "\t}\n"
      + "}";

  public final static String TEMPLATE_FILE = "<!DOCTYPE html>\n"
      + "<html>\n"
      + "<head>\n"
      + "</head>\n"
      + "<body>\n"
      + "<span th:text=\"${alert}\">1</span><br>\n"
      + "<span th:text=\"${heap_usage}\">1</span><br>\n"
      + "<span th:text=\"${endpoint_hits}\">1</span><br>\n"
      + "</body>\n"
      + "</html>\n";


  public static final String ALL_TEMPLATE_REQUEST_URL = BASEURL + "/" + VERSION + "/tenants/" + TENANT_UUID + "/templates";
  public static final String TEMPLATE_REQUEST_URL = ALL_TEMPLATE_REQUEST_URL + "/" + TEMPLATE_UUID;

  public static final String MATCHERS_REQUEST_URI = "/tenants/" + TENANT_UUID + "/templates/" + TEMPLATE_UUID + "/matchers";
  public static final String ALL_MATCHERS_REQUEST_URL = BASEURL + "/" + VERSION + MATCHERS_REQUEST_URI;
  public static final String MATCHERS_REQUEST_URL = ALL_MATCHERS_REQUEST_URL + "/" + MATCHER_UUID;

  public static final String RECIPIENTS_REQUEST_URI = MATCHERS_REQUEST_URI + "/" + MATCHER_UUID + "/recipients";
  public static final String RECIPIENTS_REQUEST_URL = BASEURL + "/" + VERSION + RECIPIENTS_REQUEST_URI;

  public static final String RECIPIENTS_JSON = "{\"recipients\":[\"dat.nguyen@notification.ge.com\"]}";

  public static final String TEMPLATE_JSON =  "{\"bodyTemplate\":\"<!DOCTYPE html>\\n<html>\\n<head>\\n</head>\\n<body>\\n<span th:text=\\\"${alert}\\\">1</span><br>\\n<span th:text=\\\"${heap_usage}\\\">1</span><br>\\n<span th:text=\\\"${endpoint_hits}\\\">1</span><br>\\n</body>\\n</html>\\n\",\"templateType\":\"THYMELEAF\",\"interval\":0,\"subjectTemplate\":\"Usage Report\",\"templateUuid\":\"a2042b41-3f92-487a-a777-3086c5271191\",\"notificationType\":\"EMAIL\",\"description\":\"Usage alert template\",\"name\":\"usage_alert\",\"domain\":\"ev.notification.ge.com\",\"expiry\":0}";
  public static final String MATCHER_JSON = "{\"templateUuid\":\"a2042b41-3f92-487a-a777-3086c5271191\",\"matchers\":\"$.[?(@.alert in ['HIGH'])]\",\"matchersUuid\":\"267b4831-d628-4486-a72f-b85551fb76f3\",\"createdDate\":1494362598479}";
  public static final String RECIPIENT_RESPONSE_JSON = "{\"recipientId\":100,\"recipientUuid\":\"cdb3b1f0-979e-44b1-837f-4c8e5e408120\",\"recipients\":\"test@notification.ge.com\",\"matchersUuid\":\"267b4831-d628-4486-a72f-b85551fb76f3\"}";

}
