package com.ge.ev.notification.vcap.domain;

import static com.ge.ev.notification.Constants.VCAP_NOTIFICATION_SERVICE;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceEnvironmentElementTest {

  @Test
  public void TestServiceEnvironmentException() {
     NotificationServiceEnvironmentElement notificationServiceEnvironmentElement = NotificationServiceEnvironmentElement.toObject("{}");
     assert(notificationServiceEnvironmentElement.getCredentials() == null);
     assert(notificationServiceEnvironmentElement.getLabel() == null);
     assert(notificationServiceEnvironmentElement.getName() == null);
     assert(notificationServiceEnvironmentElement.getName() == null);
     assert(notificationServiceEnvironmentElement.getTags() == null);

    notificationServiceEnvironmentElement = NotificationServiceEnvironmentElement.toObject(VCAP_NOTIFICATION_SERVICE);
    assert(notificationServiceEnvironmentElement.getLabel().equals("notification"));
    assert(notificationServiceEnvironmentElement.getPlan().equals("Beta"));
    assert(notificationServiceEnvironmentElement.getName().equals("notification-test"));
    assert(notificationServiceEnvironmentElement.getTags().get(0).equals("notification"));
    assert(notificationServiceEnvironmentElement.getCredentials().getZoneOauthScope().equals("notification.zone.bfb2c110-50cc-41b9-ac3a-198c7598b4b5.user"));
    assert(notificationServiceEnvironmentElement.getCredentials().getTrustedIssuerIds().equals("https://www.predix.io/oauth/token"));
    assert(notificationServiceEnvironmentElement.getCredentials().getVersion().equals("v1"));
    assert(notificationServiceEnvironmentElement.getCredentials().getTenantUuid().equals("2e04e462-7df2-4a15-98ec-f707cb98963c"));
    assert(notificationServiceEnvironmentElement.getCredentials().getCatalogUri().equals("https://ev-notification-service.run.aws-usw02-pr.ice.predix.io"));

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
    
    notificationServiceEnvironmentElement = NotificationServiceEnvironmentElement.toObject(map);
    assert(notificationServiceEnvironmentElement.getLabel().equals("notification"));
    assert(notificationServiceEnvironmentElement.getPlan().equals("Beta"));
    assert(notificationServiceEnvironmentElement.getName().equals("notification-test"));
    assert(notificationServiceEnvironmentElement.getTags().get(0).equals("notification"));
    assert(notificationServiceEnvironmentElement.getCredentials().getZoneOauthScope().equals("notification.zone.bfb2c110-50cc-41b9-ac3a-198c7598b4b5.user"));
    assert(notificationServiceEnvironmentElement.getCredentials().getTrustedIssuerIds().equals("https://www.predix.io/oauth/token"));
    assert(notificationServiceEnvironmentElement.getCredentials().getVersion().equals("v1"));
    assert(notificationServiceEnvironmentElement.getCredentials().getTenantUuid().equals("2e04e462-7df2-4a15-98ec-f707cb98963c"));
    assert(notificationServiceEnvironmentElement.getCredentials().getCatalogUri().equals("https://ev-notification-service.run.aws-usw02-pr.ice.predix.io"));
  }

}