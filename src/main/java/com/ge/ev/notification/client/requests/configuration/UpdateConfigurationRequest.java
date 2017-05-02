package com.ge.ev.notification.client.requests.configuration;

import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/2/17.
 */
public class UpdateConfigurationRequest extends CreateConfigurationRequest {

  protected UpdateConfigurationRequest(UpdateConfigurationRequestBuilder builder) {
    super(builder);
    HttpPut put = new HttpPut(getRequestUrl());

    this.notificationRequestBody = builder.getConfigurationRequestBody();

    try {
      put.setEntity(new StringEntity(this.notificationRequestBody.toJson()));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = put;
  }

  public static class UpdateConfigurationRequestBuilder extends CreateConfigurationRequestBuilder
  {
    ConfigurationRequestBody configurationRequestBody;

    public UpdateConfigurationRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public ConfigurationRequestBody getConfigurationRequestBody() {
      return configurationRequestBody;
    }

    public UpdateConfigurationRequestBuilder setConfigurationRequestBody(ConfigurationRequestBody configurationRequestBody) {
      this.configurationRequestBody = configurationRequestBody;
      return this;
    }

    public UpdateConfigurationRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public UpdateConfigurationRequestBuilder setConfigurationUuid(String configurationUuid) {
      this.configurationUuid = configurationUuid;
      return this;
    }

    public UpdateConfigurationRequest build()
    {
      return new UpdateConfigurationRequest(this);
    }
  }

}
