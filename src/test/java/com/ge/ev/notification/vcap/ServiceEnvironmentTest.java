package com.ge.ev.notification.vcap;

import static com.ge.ev.notification.Constants.VCAP_SERVICE_JSON;

import com.ge.ev.notification.vcap.domain.NotificationServiceEnvironmentElement;
import com.ge.ev.notification.vcap.domain.NotificationServiceVcapEnvironmentElementCredentials;
import com.ge.ev.notification.vcap.exceptions.ServiceEnvironmentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceEnvironmentTest
{
  @Test
  public void TestServiceEnvironment()
  {

    //null test
    try {
      ServiceEnvironment serviceEnvironment = new ServiceEnvironment();
    } catch (ServiceEnvironmentException e) {
      assert(true);
    }

    //Test for manual environment
    try {
      ServiceEnvironment serviceEnvironment = new ServiceEnvironment(VCAP_SERVICE_JSON);
      NotificationServiceEnvironmentElement notificationServiceEnvironmentElement = serviceEnvironment.getNotificationServiceElementByName("notification-hms");
      assert(notificationServiceEnvironmentElement!=null);

      NotificationServiceVcapEnvironmentElementCredentials credentials = notificationServiceEnvironmentElement.getCredentials();
      assert(credentials != null);
      assert(credentials.getCatalogUri().equals("https://ev-notification-service.run.aws-usw02-pr.ice.predix.io"));
      assert(credentials.getTenantUuid().equals("244a6f32-b781-48c2-82a4-35f6b6d0feab"));
      assert(credentials.getVersion().equals(""));
      assert(credentials.getTrustedIssuerIds().equals("https://a9e8f016-ff01-43f7-a109-21d30b272fbd.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token"));
      assert(credentials.getZoneOauthScope().equals("notification.zone.244a6f32-b781-48c2-82a4-35f6b6d0feab.user"));
    } catch (ServiceEnvironmentException e) {
      e.printStackTrace();
      assert(false);
    }

    try {
      ServiceEnvironment serviceEnvironment = new ServiceEnvironment("not json");
    } catch (ServiceEnvironmentException e) {
      assert(true);
    }

  }

}
