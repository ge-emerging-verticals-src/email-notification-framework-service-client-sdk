package com.ge.ev.notification.client.requests.configuration;

import org.apache.http.client.methods.HttpDelete;

/**
 * Created by 212391398 on 5/1/17.
 */
public class DeleteConfigurationRequest extends ConfigurationsRequest {
  private DeleteConfigurationRequest(DeleteConfigurationsRequestBuilder builder)
  {
    super(builder);
    super.requestBase = new HttpDelete(getRequestUrl());
  }

  public static class DeleteConfigurationsRequestBuilder extends ConfigurationsRequestBuilder
  {
    public DeleteConfigurationsRequestBuilder( String baseUrl, String version, String tenantUuid)
    {
      setBaseUrl(baseUrl);
      setVersion(version);
      setTenantUuid(tenantUuid);
    }

    public DeleteConfigurationsRequestBuilder setTenantUuid(String tenantUuid) {
      super.tenantUuid = tenantUuid;
      return this;
    }

    public DeleteConfigurationsRequestBuilder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public DeleteConfigurationsRequestBuilder setVersion(String version) {
      this.version = version;
      return this;
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
