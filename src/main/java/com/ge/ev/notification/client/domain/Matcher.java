package com.ge.ev.notification.client.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Matcher extends JsonObject {

  private String templateUuid;

  private String matchers;

  private String matchersUuid;

  private Long createdDate;

  public String getTemplateUuid() {
    return templateUuid;
  }

  public String getMatchers() {
    return matchers;
  }

  public String getMatchersUuid() {
    return matchersUuid;
  }

  public Long getCreatedDate() {
    return createdDate;
  }

  public static Matcher toObject(Map<String, Object> map) {
    return JsonObject.toObject(map, Matcher.class);
  }
}