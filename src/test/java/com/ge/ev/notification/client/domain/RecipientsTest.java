package com.ge.ev.notification.client.domain;

import static com.ge.ev.notification.Constants.RECIPIENT_RESPONSE_JSON;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class RecipientsTest {

  @Test
  public void TestRecipientsTest() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("recipientId", 100L);
    map.put("recipients", "test@notification.ge.com");
    map.put("matchersUuid", "267b4831-d628-4486-a72f-b85551fb76f3");
    map.put("recipientUuid", "cdb3b1f0-979e-44b1-837f-4c8e5e408120");

    Recipient recipient = Recipient.toObject(map);
    assert(recipient != null);
    assert(recipient.getRecipientId() == 100L);
    assert(recipient.getRecipients().equals("test@notification.ge.com"));
    assert(recipient.getMatchersUuid().equals("267b4831-d628-4486-a72f-b85551fb76f3"));
    assert(recipient.getRecipientUuid().equals("cdb3b1f0-979e-44b1-837f-4c8e5e408120"));
    assert(recipient.toJson().equals(RECIPIENT_RESPONSE_JSON));
  }

}
