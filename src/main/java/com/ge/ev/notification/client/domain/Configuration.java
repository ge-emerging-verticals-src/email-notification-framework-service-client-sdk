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

  public static Configuration toObject(Map<String, Object> map)
  {
    return JsonObject.toObject(map, Configuration.class);
  }

  public static class ConfigurationBuilder
  {

    private String protocol;
    private String host;
    private Integer port;
    private Boolean smtpAuth;
    private Boolean smtpStarttlsEnable;
    private String mailFrom;
    private String mailUsername;
    private String mailPassword;
    private String mailReturnPath;;

    public ConfigurationBuilder() {
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

    public String getMailReturnPath() {
      return mailReturnPath;
    }

    public ConfigurationBuilder setProtocol(String protocol) {
      this.protocol = protocol;
      return this;
    }

    public ConfigurationBuilder setHost(String host) {
      this.host = host;
      return this;
    }

    public ConfigurationBuilder setPort(Integer port) {
      this.port = port;
      return this;
    }

    public ConfigurationBuilder setSmtpAuth(Boolean smtpAuth) {
      this.smtpAuth = smtpAuth;
      return this;
    }

    public ConfigurationBuilder setSmtpStarttlsEnable(Boolean smtpStarttlsEnable) {
      this.smtpStarttlsEnable = smtpStarttlsEnable;
      return this;
    }

    public ConfigurationBuilder setMailFrom(String mailFrom) {
      this.mailFrom = mailFrom;
      return this;
    }

    public ConfigurationBuilder setMailUsername(String mailUsername) {
      this.mailUsername = mailUsername;
      return this;
    }

    public ConfigurationBuilder setMailPassword(String mailPassword) {
      this.mailPassword = mailPassword;
      return this;
    }

    public ConfigurationBuilder setMailReturnPath(String mailReturnPath) {
      this.mailReturnPath = mailReturnPath;
      return this;
    }

    public Configuration build()
    {
      Configuration configuration = new Configuration();

      configuration.protocol =   getProtocol();
      configuration.host =  getHost();
      configuration.port =  getPort();
      configuration.smtpAuth = getSmtpAuth();
      configuration.smtpStarttlsEnable =  getSmtpStarttlsEnable();
      configuration.mailFrom =  getMailFrom();
      configuration.mailUsername =   getMailUsername();
      configuration.mailPassword = getMailPassword();
      configuration.mailReturnPath =  getMailReturnPath();

      return configuration;
    }

  }
}
