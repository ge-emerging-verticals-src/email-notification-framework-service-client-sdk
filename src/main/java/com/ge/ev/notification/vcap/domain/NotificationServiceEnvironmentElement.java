package com.ge.ev.notification.vcap.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ge.ev.notification.client.json.JsonObject;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 212391398 on 4/18/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationServiceEnvironmentElement extends JsonObject {
  private List<String> tags;

  private String plan;

  private String name;

  @JsonProperty("credentials")
  private NotificationServiceVcapEnvironmentElementCredentials credentials;

  private String label;

  public List<String> getTags ()
  {
    return tags;
  }

  public void setTags (List<String> tags)
  {
    this.tags = tags;
  }

  public String getPlan ()
  {
    return plan;
  }

  public void setPlan (String plan)
  {
    this.plan = plan;
  }

  public String getName ()
  {
    return name;
  }

  public void setName (String name)
  {
    this.name = name;
  }

  public NotificationServiceVcapEnvironmentElementCredentials getCredentials ()
  {
    return credentials;
  }

  public void setCredentials (NotificationServiceVcapEnvironmentElementCredentials credentials)
  {
    this.credentials = credentials;
  }

  public String getLabel ()
  {
    return label;
  }

  public void setLabel (String label)
  {
    this.label = label;
  }

  public static NotificationServiceEnvironmentElement toObject(String json)
  {
    return JsonObject.toObject(json, NotificationServiceEnvironmentElement.class);
  }

  public static NotificationServiceEnvironmentElement toObject(HashMap<String, Object> map)
  {
    return JsonObject.toObject(map, NotificationServiceEnvironmentElement.class);
  }

}
