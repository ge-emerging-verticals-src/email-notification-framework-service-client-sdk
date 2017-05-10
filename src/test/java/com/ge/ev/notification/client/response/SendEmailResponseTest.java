package com.ge.ev.notification.client.response;

import static com.ge.ev.notification.Constants.NOTIFICATION_REFERENCE_UUID;
import static com.ge.ev.notification.Constants.SEND_EMAIL_REPONSE_JSON;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailResponseTest {

  @Test
  public void TestSendEmailResponse()
  {
    SendEmailResponse sendEmailResponse =  SendEmailResponse.toObject(SEND_EMAIL_REPONSE_JSON);
    assert(sendEmailResponse.getErrors().get(0).equals("test error"));
    assert(sendEmailResponse.getNotificationReferenceUuid().equals(NOTIFICATION_REFERENCE_UUID));
    assert(sendEmailResponse.getNotificationStatus().equals("NotificationEmailMessageQueued"));

    String[] errors = new String[1];
    errors[0] = "test error";
    HashMap<String, Object> map = new HashMap<>();
    map.put("errors", errors);
    map.put("notificationReferenceUuid", NOTIFICATION_REFERENCE_UUID);
    map.put("notificationStatus", "NotificationEmailMessageQueued");

    sendEmailResponse = SendEmailResponse.toObject(map);
    assert(sendEmailResponse.getErrors().get(0).equals("test error"));
    assert(sendEmailResponse.getNotificationReferenceUuid().equals(NOTIFICATION_REFERENCE_UUID));
    assert(sendEmailResponse.getNotificationStatus().equals("NotificationEmailMessageQueued"));
  }

}
