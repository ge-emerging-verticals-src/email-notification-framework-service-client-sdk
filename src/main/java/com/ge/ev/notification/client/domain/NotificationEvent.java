package com.ge.ev.notification.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationEvent extends JsonObject
{
  private Long timestamp;
  private Integer id;
  private Boolean enabled;
  private String tag;
  private Long classification;
  private String data;
  private String context;
  private Long lastUpdated;
  private String uuid;
  private String tenantUuid;

  public Long getTimestamp() {
    return timestamp;
  }

  public Integer getId() {
    return id;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public String getTag() {
    return tag;
  }

  public Long getClassification() {
    return classification;
  }

  public String getData() {
    return data;
  }

  public String getContext() {
    return context;
  }

  public Long getLastUpdated() {
    return lastUpdated;
  }

  public String getUuid() {
    return uuid;
  }

  public String getTenantUuid() {
    return tenantUuid;
  }

  public static NotificationEvent toObject(Map<String, Object> map)
  {
    return JsonObject.toObject(map, NotificationEvent.class);
  }
}
