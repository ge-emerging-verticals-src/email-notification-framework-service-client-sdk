package com.ge.ev.notification.vcap;

import com.ge.ev.notification.client.NotificationServiceClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by 212391398 on 4/28/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestNotificationClient {

  private String TENANT_UUID = "ebb09d9a-0f15-4338-b90b-038c109676ce";
  private String VERSION = "v1";
  private String BASE_URL = "https://notification.predix.io";
  private String TOKEN = "ZDA3Y2RlODQtNzVlYi00MjQ0LTkzZTItYTdiZGY2YjVjMWZhZDA3Y2RlODQtNzVlYi00MjQ0LTkzZTItYTdiZGY2YjVjMWZhZDA3Y2RlODQtNzVlYi00MjQ0LTkzZTItYTdiZGY2YjVjMWZhZDA3Y2RlODQtNzVlYi00MjQ0LTkzZTItYTdiZGY2YjVjMWZhZDA3Y2RlODQtNzVlYi00MjQ0LTkzZTItYTdiZGY2YjVjMWZhZDA3Y2RlODQtNzVlYi00MjQ0LTkzZTItYTdiZGY2YjVjMWZhZDA3Y2RlODQtNzVlYi00MjQ0LTkzZTItYTdiZGY2YjVjMWZhZDA3Y2RlODQtNzVlYi00MjQ0LTkzZTItYTdiZGY2YjVjMWZh";

  @Test
  public void NotificationClientBaseTest()
  {
    NotificationServiceClient notificationServiceClient = new NotificationServiceClient();

    notificationServiceClient.setBaseUrl(BASE_URL);
    notificationServiceClient.setVersion(VERSION);
    notificationServiceClient.setTenantUuid(TENANT_UUID);
    notificationServiceClient.setToken(TOKEN);
    
    assert(notificationServiceClient.getBaseUrl().equals(BASE_URL));
    assert(notificationServiceClient.getVersion().equals(VERSION));
    assert(notificationServiceClient.getTenantUuid().equals(TENANT_UUID));
    assert(notificationServiceClient.getToken().equals(TOKEN));
  }

}
