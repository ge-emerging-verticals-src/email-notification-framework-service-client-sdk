package com.ge.ev.notification.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Created by 212391398 on 4/18/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tenant implements Serializable{

  private String timestamp;

  private String id;

  @JsonProperty("trusted_issuers")
  private String trustedIssuers;

  private String uuid;


  @JsonProperty("event_count")
  private String eventCount;

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTrustedIssuers() {
    return trustedIssuers;
  }

  public void setTrustedIssuers(String trustedIssuers) {
    this.trustedIssuers = trustedIssuers;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getEventCount() {
    return eventCount;
  }

  public void setEventCount(String eventCount) {
    this.eventCount = eventCount;
  }

  @Override
  public String toString() {
    return "Tenant{" +
        "timestamp='" + timestamp + '\'' +
        ", id='" + id + '\'' +
        ", trustedIssuers='" + trustedIssuers + '\'' +
        ", uuid='" + uuid + '\'' +
        ", eventCount='" + eventCount + '\'' +
        '}';
  }
}
