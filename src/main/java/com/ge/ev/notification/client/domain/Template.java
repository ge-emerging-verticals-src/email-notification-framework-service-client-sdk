package com.ge.ev.notification.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Template extends JsonObject {

  private String bodyTemplate;
  private String templateType;
  private Integer interval;
  private String subjectTemplate;
  private String templateUuid;
  private String notificationType;
  private String description;
  private String name;
  private String domain;
  private Integer expiry;

  public String getBodyTemplate() {
    return bodyTemplate;
  }

  public String getTemplateType() {
    return templateType;
  }

  public Integer getInterval() {
    return interval;
  }

  public String getSubjectTemplate() {
    return subjectTemplate;
  }

  public String getTemplateUuid() {
    return templateUuid;
  }

  public String getNotificationType() {
    return notificationType;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public String getDomain() {
    return domain;
  }

  public Integer getExpiry() {
    return expiry;
  }

  public static Template toObject(Map<String, Object> map) {
    return JsonObject.toObject(map, Template.class);
  }
}