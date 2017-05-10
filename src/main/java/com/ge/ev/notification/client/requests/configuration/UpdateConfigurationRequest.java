package com.ge.ev.notification.client.requests.configuration;

import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/2/17.
 */
public class UpdateConfigurationRequest extends ConfigurationsRequest  {
  UpdateConfigurationRequestBody  updateConfigurationRequestBody;

  protected UpdateConfigurationRequest(UpdateConfigurationRequestBuilder builder) {
    super(builder);
    HttpPut put = new HttpPut(getRequestUrl());

    this.notificationRequestBody = this.updateConfigurationRequestBody = builder.getUpdateConfigurationRequestBody();

    try {
      put.setEntity(new StringEntity(this.notificationRequestBody.toJson()));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = put;
  }

  public UpdateConfigurationRequestBody getUpdateConfigurationRequestBody() {
    return this.updateConfigurationRequestBody;
  }

  public static class UpdateConfigurationRequestBuilder extends ConfigurationsRequestBuilder
  {
    UpdateConfigurationRequestBody updateConfigurationRequestBody;

    public UpdateConfigurationRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public UpdateConfigurationRequestBody getUpdateConfigurationRequestBody() {
      return updateConfigurationRequestBody;
    }

    public UpdateConfigurationRequestBuilder setUpdateConfigurationRequestBody(UpdateConfigurationRequestBody updateConfigurationRequestBody) {
      this.updateConfigurationRequestBody = updateConfigurationRequestBody;
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
