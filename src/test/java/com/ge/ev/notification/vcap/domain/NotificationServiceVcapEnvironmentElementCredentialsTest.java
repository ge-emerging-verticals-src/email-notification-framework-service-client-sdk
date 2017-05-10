package com.ge.ev.notification.vcap.domain;


import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceVcapEnvironmentElementCredentialsTest {

  @Test
  public
  void TestNotificationServiceVcapEnvironmentElementCredentials()
  {
    HashMap<String, Object> credentials = new HashMap<>();
    credentials.put("zoneOauthScope","notification.zone.bfb2c110-50cc-41b9-ac3a-198c7598b4b5.user");
    credentials.put("trustedIssuerIds","https://www.predix.io/oauth/token");
    credentials.put("version","v1");
    credentials.put("tenantUuid","2e04e462-7df2-4a15-98ec-f707cb98963c");
    credentials.put("catalogUri","https://ev-notification-service.run.aws-usw02-pr.ice.predix.io");

    NotificationServiceVcapEnvironmentElementCredentials notificationServiceVcapEnvironmentElementCredentials  = NotificationServiceVcapEnvironmentElementCredentials.toObject(credentials);
    assert(notificationServiceVcapEnvironmentElementCredentials.getZoneOauthScope().equals("notification.zone.bfb2c110-50cc-41b9-ac3a-198c7598b4b5.user"));
    assert(notificationServiceVcapEnvironmentElementCredentials.getTrustedIssuerIds().equals("https://www.predix.io/oauth/token"));
    assert(notificationServiceVcapEnvironmentElementCredentials.getVersion().equals("v1"));
    assert(notificationServiceVcapEnvironmentElementCredentials.getTenantUuid().equals("2e04e462-7df2-4a15-98ec-f707cb98963c"));
    assert(notificationServiceVcapEnvironmentElementCredentials.getCatalogUri().equals("https://ev-notification-service.run.aws-usw02-pr.ice.predix.io"));

    String json = "{\"zoneOauthScope\":\"notification.zone.bfb2c110-50cc-41b9-ac3a-198c7598b4b5.user\",\"tenantUuid\":\"2e04e462-7df2-4a15-98ec-f707cb98963c\",\"catalogUri\":\"https://ev-notification-service.run.aws-usw02-pr.ice.predix.io\",\"version\":\"v1\",\"trustedIssuerIds\":\"https://www.predix.io/oauth/token\"}";

    assert(notificationServiceVcapEnvironmentElementCredentials.toJson().equals(json));


    NotificationServiceVcapEnvironmentElementCredentials notificationServiceVcapEnvironmentElementCredentials2  = NotificationServiceVcapEnvironmentElementCredentials.toObject(json);
    assert(notificationServiceVcapEnvironmentElementCredentials2.getZoneOauthScope().equals("notification.zone.bfb2c110-50cc-41b9-ac3a-198c7598b4b5.user"));
    assert(notificationServiceVcapEnvironmentElementCredentials2.getTrustedIssuerIds().equals("https://www.predix.io/oauth/token"));
    assert(notificationServiceVcapEnvironmentElementCredentials2.getVersion().equals("v1"));
    assert(notificationServiceVcapEnvironmentElementCredentials2.getTenantUuid().equals("2e04e462-7df2-4a15-98ec-f707cb98963c"));
    assert(notificationServiceVcapEnvironmentElementCredentials2.getCatalogUri().equals("https://ev-notification-service.run.aws-usw02-pr.ice.predix.io"));
  }
}
