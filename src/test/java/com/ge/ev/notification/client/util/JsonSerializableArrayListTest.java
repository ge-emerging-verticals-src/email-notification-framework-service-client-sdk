package com.ge.ev.notification.client.util;

import static com.ge.ev.notification.Constants.EMAIL;
import static com.ge.ev.notification.Constants.NAME;
import static com.ge.ev.notification.Constants.RECIPIENTS_JSON_ARRAY;
import static com.ge.ev.notification.Constants.TYPE;

import com.ge.ev.notification.client.requests.email.SendEmailRequestBodyRecipient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class JsonSerializableArrayListTest {

  @Test
  public void TestJsonSerializableListTest() {
    SendEmailRequestBodyRecipient sendEmailRequestBodyRecipient = new SendEmailRequestBodyRecipient.SendEmailRequestBodyRecipientBuilder(
        NAME, EMAIL, TYPE).build();
    SendEmailRequestBodyRecipient sendEmailRequestBodyRecipient2 = new SendEmailRequestBodyRecipient.SendEmailRequestBodyRecipientBuilder(
        NAME, EMAIL, TYPE).build();

    JsonSerializableArrayList<SendEmailRequestBodyRecipient> jsonSerializableArrayList = new JsonSerializableArrayList<>();
    jsonSerializableArrayList.add(sendEmailRequestBodyRecipient);
    jsonSerializableArrayList.add(sendEmailRequestBodyRecipient2);

    assert (jsonSerializableArrayList.toJson().equals(RECIPIENTS_JSON_ARRAY));
  }
}
