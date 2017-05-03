package com.ge.ev.notification.client.response;

import com.ge.ev.notification.client.json.JsonObject;
import java.util.List;
import java.util.Map;

/**
 * Created by 212391398 on 5/2/17.
 */
public class SendEmailResponse extends JsonObject {
  private String notificationStatus;

  private List<String> errors;

  private String notificationReferenceUuid;

  public String getNotificationStatus ()
  {
    return notificationStatus;
  }

  public List<String> getErrors ()
  {
    return errors;
  }

  public String getNotificationReferenceUuid ()
  {
    return notificationReferenceUuid;
  }

  public static SendEmailResponse toObject(String json)
  {
    return JsonObject.toObject(json, SendEmailResponse.class);
  }

  public static SendEmailResponse toObject(Map<String, Object> map)
  {
    return JsonObject.toObject(map, SendEmailResponse.class);
  }
}
