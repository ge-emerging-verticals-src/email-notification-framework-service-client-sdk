package com.ge.ev.notification.client.domain;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NotificationEventTest {

  @Test
  public void TestNotificationEventTest() {
    HashMap<String, Object> map = new HashMap<>();

    map.put("id", 97);
    map.put("uuid", "bc8d41a6-de37-414b-b167-dddeb58e9956");
    map.put("tenantUuid", "14a6ae12-5dce-44ea-8b42-5bc09dafa9a0");
    map.put("context", "77e36836-cf4d-49a7-81fb-3a5311a454ff");
    map.put("tag", "bba698ab-a5e1-431e-a307-c9a80f8b401d");
    map.put("classification", 1018);
    map.put("enabled", true);
    map.put("timestamp", 1493845634683L);
    map.put("lastUpdated", 1493845634683L);
    map.put("data", "Webhook successful");

    NotificationEvent notificationEvent = NotificationEvent.toObject(map);

    assert(notificationEvent.getId().equals(97));
    assert(notificationEvent.getUuid().equals("bc8d41a6-de37-414b-b167-dddeb58e9956"));
    assert(notificationEvent.getTenantUuid().equals("14a6ae12-5dce-44ea-8b42-5bc09dafa9a0"));
    assert(notificationEvent.getContext().equals("77e36836-cf4d-49a7-81fb-3a5311a454ff"));
    assert(notificationEvent.getTag().equals("bba698ab-a5e1-431e-a307-c9a80f8b401d"));
    assert(notificationEvent.getClassification().equals(1018L));
    assert(notificationEvent.getEnabled()==true);
    assert(notificationEvent.getTimestamp().equals(1493845634683L));
    assert(notificationEvent.getLastUpdated().equals(1493845634683L));
    assert(notificationEvent.getData().equals("Webhook successful"));
  }

}
