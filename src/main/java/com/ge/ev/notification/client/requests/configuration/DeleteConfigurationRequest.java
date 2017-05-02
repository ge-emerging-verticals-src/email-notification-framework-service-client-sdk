package com.ge.ev.notification.client.requests.configuration;

import org.apache.http.client.methods.HttpDelete;

/**
 * Created by 212391398 on 5/1/17.
 */
public class DeleteConfigurationRequest extends ConfigurationsRequest {
  private DeleteConfigurationRequest(ConfigurationsRequestBuilder builder)
  {
    super(builder);
    super.requestBase = new HttpDelete(getRequestUrl());
  }

  public static class DeleteConfigurationsRequestBuilder extends ConfigurationsRequestBuilder
  {
    public DeleteConfigurationsRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public DeleteConfigurationsRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public DeleteConfigurationsRequestBuilder setConfigurationUuid(String configurationUuid) {
      this.configurationUuid = configurationUuid;
      return this;
    }

    public DeleteConfigurationRequest build()
    {
      return new DeleteConfigurationRequest(this);
    }
  }

}
