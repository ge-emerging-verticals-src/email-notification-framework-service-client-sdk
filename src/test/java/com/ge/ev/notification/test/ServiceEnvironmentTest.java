package com.ge.ev.notification.test;

import com.ge.ev.notification.vcap.ServiceEnvironment;
import com.ge.ev.notification.vcap.domain.NotificationServiceEnvironmentElement;
import com.ge.ev.notification.vcap.domain.NotificationServiceVcapEnvironmentElementCredentials;
import com.ge.ev.notification.vcap.exceptions.ServiceEnvironmentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by 212391398 on 4/18/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ServiceEnvironmentTest
{

  private static final String json = "{\n"
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
      ServiceEnvironment serviceEnvironment = new ServiceEnvironment(json);
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
  }

}
