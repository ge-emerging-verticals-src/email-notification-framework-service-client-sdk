package com.ge.ev.notification.test;

import static com.ge.ev.notification.test.Constants.BASEURL;
import static com.ge.ev.notification.test.Constants.TENANT_UUID;
import static com.ge.ev.notification.test.Constants.VERSION;

import com.ge.ev.notification.client.NotificationServiceClient;
import com.ge.ev.notification.client.NotificationServiceClient.NotificationServiceClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by 212391398 on 4/28/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NotificationClientTest {

  @Test
  public void TestNotificationClientBase()
  {
    NotificationServiceClient notificationServiceClient = new NotificationServiceClientBuilder()
        .setBaseUrl(BASEURL)
        .setVersion(VERSION)
        .setTenantUuid(TENANT_UUID)
        .build();
    
    assert(notificationServiceClient.getBaseUrl().equals(BASEURL));
    assert(notificationServiceClient.getVersion().equals(VERSION));
    assert(notificationServiceClient.getTenantUuid().equals(TENANT_UUID));
  }

}
