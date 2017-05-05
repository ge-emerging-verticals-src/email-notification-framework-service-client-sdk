package com.ge.ev.notification.client.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipient extends JsonObject {

  private Integer recipientId;

  private String recipientUuid;

  private String recipients;

  private String matchersUuid;

  public Integer getRecipientId() {
    return recipientId;
  }

  public String getRecipientUuid() {
    return recipientUuid;
  }

  public String getRecipients() {
    return recipients;
  }

  public String getMatchersUuid() {
    return matchersUuid;
  }

  public static Recipient toObject(Map<String, Object> map) {
    return JsonObject.toObject(map, Recipient.class);
  }
}