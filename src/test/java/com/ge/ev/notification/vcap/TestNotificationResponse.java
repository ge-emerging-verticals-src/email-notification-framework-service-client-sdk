package com.ge.ev.notification.vcap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ev.notification.client.response.NotificationServiceResponse;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by 212391398 on 4/28/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestNotificationResponse {

  private static final String json = "{\"payload\":{},\"uuid\":\"7e4d5743-1f02-48dd-b3d2-678ee615270a\",\"status\":1000,\"message\":\"OK\",\"timestamp\":1493407148536}";

  @Test
  public void TestNotificationResponseTest()
  {
    ObjectMapper mapper = new ObjectMapper();

    try {
      NotificationServiceResponse notificationServiceResponse = mapper.readValue(json, NotificationServiceResponse.class);
      assert(notificationServiceResponse != null);
      assert(notificationServiceResponse.getUuid().equals("7e4d5743-1f02-48dd-b3d2-678ee615270a"));
      assert(notificationServiceResponse.getStatus() == 1000L);
      assert(notificationServiceResponse.getMessage().equals("OK"));
      assert(notificationServiceResponse.getTimestamp() == 1493407148536L);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
