package com.ge.ev.notification.client.json;

import static com.ge.ev.notification.Constants.SAMPLE_JSON;
import static com.ge.ev.notification.Constants.TEST_ERROR;
import static com.ge.ev.notification.Constants.TEST_MESSAGE;
import static com.ge.ev.notification.Constants.TEST_UUID;

import com.ge.ev.notification.client.response.SendEmailResponse;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class JsonObjectTest {

  @Test
  public void TestJsonObject() {
    SendEmailResponse sendEmailResponse = JsonObject.toObject(SAMPLE_JSON, SendEmailResponse.class);
    assert(sendEmailResponse.getErrors().get(0).equals(TEST_ERROR));
    assert(sendEmailResponse.getNotificationReferenceUuid().equals(TEST_UUID));
    assert(sendEmailResponse.getNotificationStatus().equals(TEST_MESSAGE));
  }

  @Test
  public void TestJsonObjectFromHashMap() {
    HashMap<String, Object> map = new HashMap<>();

    ArrayList<String> errors = new ArrayList<>();
    errors.add(TEST_ERROR);

    map.put("errors",  errors);
    map.put("notificationReferenceUuid", TEST_UUID);
    map.put("notificationStatus", TEST_MESSAGE);

    SendEmailResponse sendEmailResponse = JsonObject.toObject(map, SendEmailResponse.class);

    assert(sendEmailResponse.getErrors().get(0).equals(TEST_ERROR));
    assert(sendEmailResponse.getNotificationReferenceUuid().equals(TEST_UUID));
    assert(sendEmailResponse.getNotificationStatus().equals(TEST_MESSAGE));
  }

  @Test
  public void TestJsonObjectToJson()
  {
    HashMap<String, Object> map = new HashMap<>();

    ArrayList<String> errors = new ArrayList<>();
    errors.add(TEST_ERROR);

    map.put("errors",  errors);
    map.put("notificationReferenceUuid", TEST_UUID);
    map.put("notificationStatus", TEST_MESSAGE);

    SendEmailResponse sendEmailResponse = JsonObject.toObject(map, SendEmailResponse.class);

    assert(sendEmailResponse.toJson().equals(SAMPLE_JSON));
  }

  @Test
  public void TestJsonObjectExceptions() {
      SendEmailResponse sendEmailResponse = JsonObject.toObject("", SendEmailResponse.class);
      assert(sendEmailResponse == null);

      HashMap<String, Object> map = new HashMap<>();
      JsonObject.toObject(map, SendEmailResponse.class);
      assert(sendEmailResponse == null);
  }

}
