package com.ge.ev.notification.client.requests.configuration;

import org.apache.http.client.methods.HttpGet;

/**
 * Created by 212391398 on 5/1/17.
 */
public class GetConfigurationsRequest extends ConfigurationsRequest {
  private GetConfigurationsRequest(GetConfigurationsRequestBuilder builder)
  {
    super(builder);
    super.requestBase = new HttpGet(getRequestUrl());
  }

  public static class GetConfigurationsRequestBuilder extends ConfigurationsRequestBuilder
  {
    public GetConfigurationsRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public GetConfigurationsRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public GetConfigurationsRequestBuilder setConfigurationUuid(String configurationUuid) {
      this.configurationUuid = configurationUuid;
      return this;
    }

    public GetConfigurationsRequest build()
    {
      return new GetConfigurationsRequest(this);
    }
  }

}
