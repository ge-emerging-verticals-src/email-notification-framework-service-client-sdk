package com.ge.ev.notification.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;

/**
 * Created by 212391398 on 4/28/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationServiceResponse extends JsonObject {
    private Long timestamp;

    private String message;

    private Long status;

    private Object payload;

    private String uuid;

    public Long getTimestamp ()
    {
      return timestamp;
    }

    public String getMessage ()
    {
      return message;
    }

    public Long getStatus ()
    {
      return status;
    }
  
    public Object getPayload ()
    {
      return payload;
    }

    public String getUuid ()
    {
      return uuid;
    }

  @Override
  public String toString() {
    return "NotificationServiceResponse{" +
        "timestamp='" + timestamp + '\'' +
        ", message='" + message + '\'' +
        ", status='" + status + '\'' +
        ", payload='" + payload + '\'' +
        ", uuid='" + uuid + '\'' +
        '}';
  }
}
