package com.ge.ev.notification.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;

/**
 * Created by 212391398 on 4/18/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tenant extends JsonObject{

  private Long timestamp;
  private Long id;
  private String successWebhook;
  private Long monthlyLimit;
  private String bindingId;
  private String trustedIssuers;
  private Long dailyLimit;
  private String planId;
  private String uuid;
  private String failWebhook;

  public Long getTimestamp() {
    return timestamp;
  }

  public Long getId() {
    return id;
  }

  public String getSuccessWebhook() {
    return successWebhook;
  }

  public Long getMonthlyLimit() {
    return monthlyLimit;
  }

  public String getBindingId() {
    return bindingId;
  }

  public String getTrustedIssuers() {
    return trustedIssuers;
  }

  public Long getDailyLimit() {
    return dailyLimit;
  }

  public String getPlanId() {
    return planId;
  }

  public String getUuid() {
    return uuid;
  }

  public String getFailWebhook() {
    return failWebhook;
  }
}
