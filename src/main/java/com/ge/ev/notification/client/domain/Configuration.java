package com.ge.ev.notification.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ge.ev.notification.client.json.JsonObject;
import java.util.Map;

/**
 * Created by 212391398 on 5/1/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Configuration extends JsonObject {

  private Long id;
  private String uuid;
  private String tenantUuid;
  private String protocol;
  private String host;
  private Integer port;
  private Boolean smtpAuth;
  private Boolean smtpStarttlsEnable;
  private String mailFrom;
  private String mailUsername;
  private String mailPassword;
  private Long timestamp;
  private Long lastUpdated;
  private String mailReturnPath;

  public Long getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public String getTenantUuid() {
    return tenantUuid;
  }

  public String getProtocol() {
    return protocol;
  }

  public String getHost() {
    return host;
  }

  public Integer getPort() {
    return port;
  }

  public Boolean getSmtpAuth() {
    return smtpAuth;
  }

  public Boolean getSmtpStarttlsEnable() {
    return smtpStarttlsEnable;
  }

  public String getMailFrom() {
    return mailFrom;
  }

  public String getMailUsername() {
    return mailUsername;
  }

  public String getMailPassword() {
    return mailPassword;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public Long getLastUpdated() {
    return lastUpdated;
  }

  public String getMailReturnPath() {
    return mailReturnPath;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public void setSmtpAuth(Boolean smtpAuth) {
    this.smtpAuth = smtpAuth;
  }

  public void setSmtpStarttlsEnable(Boolean smtpStarttlsEnable) {
    this.smtpStarttlsEnable = smtpStarttlsEnable;
  }

  public void setMailFrom(String mailFrom) {
    this.mailFrom = mailFrom;
  }

  public void setMailUsername(String mailUsername) {
    this.mailUsername = mailUsername;
  }

  public void setMailPassword(String mailPassword) {
    this.mailPassword = mailPassword;
  }

  public void setMailReturnPath(String mailReturnPath) {
    this.mailReturnPath = mailReturnPath;
  }
  
  public static Configuration toObject(Map<String, Object> map)
  {
    return JsonObject.toObject(map, Configuration.class);
  }
}
