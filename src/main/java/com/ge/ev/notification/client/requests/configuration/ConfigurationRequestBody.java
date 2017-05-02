package com.ge.ev.notification.client.requests.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;

/**
 * Created by 212391398 on 5/1/17.
 */
@JsonInclude(Include.NON_NULL)
public class ConfigurationRequestBody extends JsonObject implements NotificationRequestBody {

  private Configuration configuration;

  private  ConfigurationRequestBody(ConfigurationRequestBodyBuilder configurationRequestBodyBuilder)
  {
    this.configuration = new Configuration();
    configuration.setHost(configurationRequestBodyBuilder.getHost());
    configuration.setMailFrom(configurationRequestBodyBuilder.getMailFrom());
    configuration.setMailPassword(configurationRequestBodyBuilder.getMailPassword());
    configuration.setMailReturnPath(configurationRequestBodyBuilder.getMailReturnPath());
    configuration.setMailUsername(configurationRequestBodyBuilder.getMailUsername());
    configuration.setPort(configurationRequestBodyBuilder.getPort());
    configuration.setSmtpAuth(configurationRequestBodyBuilder.getSmtpAuth());
    configuration.setSmtpStarttlsEnable(configurationRequestBodyBuilder.getSmtpStarttlsEnable());
    configuration.setProtocol(configurationRequestBodyBuilder.getProtocol());
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  @Override
  public String toJson()
  {
    return configuration.toJson();
  }

  public static class ConfigurationRequestBodyBuilder
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

    public ConfigurationRequestBodyBuilder() {}

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

    public ConfigurationRequestBodyBuilder setProtocol(String protocol) {
      this.protocol = protocol;
      return this;
    }

    public ConfigurationRequestBodyBuilder setHost(String host) {
      this.host = host;
      return this;
    }

    public ConfigurationRequestBodyBuilder setPort(Integer port) {
      this.port = port;
      return this;
    }

    public ConfigurationRequestBodyBuilder setSmtpAuth(Boolean smtpAuth) {
      this.smtpAuth = smtpAuth;
      return this;
    }

    public ConfigurationRequestBodyBuilder setSmtpStarttlsEnable(Boolean smtpStarttlsEnable) {
      this.smtpStarttlsEnable = smtpStarttlsEnable;
      return this;
    }

    public ConfigurationRequestBodyBuilder setMailFrom(String mailFrom) {
      this.mailFrom = mailFrom;
      return this;
    }

    public ConfigurationRequestBodyBuilder setMailUsername(String mailUsername) {
      this.mailUsername = mailUsername;
      return this;
    }

    public ConfigurationRequestBodyBuilder setMailPassword(String mailPassword) {
      this.mailPassword = mailPassword;
      return this;
    }

    public ConfigurationRequestBodyBuilder setMailReturnPath(String mailReturnPath) {
      this.mailReturnPath = mailReturnPath;
      return this;
    }

    public ConfigurationRequestBody build()
    {
      return new ConfigurationRequestBody(this);
    }

  }

}
