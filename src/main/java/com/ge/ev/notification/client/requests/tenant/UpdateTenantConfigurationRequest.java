package com.ge.ev.notification.client.requests.tenant;

import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 4/18/17.
 */
public class UpdateTenantConfigurationRequest extends TenantRequest {

  private UpdateTenantConfigurationRequest(UpdateTenantConfigurationRequestBuilder builder)
  {
    super(builder);
    this.notificationRequestBody = builder.updateTenantConfigurationRequestBody;
    HttpPut put = new HttpPut(getRequestUrl());
    try {
      put.setEntity(new StringEntity(this.notificationRequestBody.toJson()));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = put;
  }

  public static class UpdateTenantConfigurationRequestBuilder extends NotificationRequestBuilder
  {
    private UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody;

    public UpdateTenantConfigurationRequestBuilder(String baseUrl, String version, String tenantUuid)
    {
      setBaseUrl(baseUrl);
      setTenantUuid(tenantUuid);
      setVersion(version);
    }


    public UpdateTenantConfigurationRequestBuilder setTenantUuid(String tenantUuid) {
      super.tenantUuid = tenantUuid;
      return this;
    }

    public UpdateTenantConfigurationRequestBuilder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public UpdateTenantConfigurationRequestBuilder setVersion(String version) {
      this.version = version;
      return this;
    }

    public UpdateTenantConfigurationRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public UpdateTenantConfigurationRequestBody getUpdateTenantConfigurationRequestBody() {
      return updateTenantConfigurationRequestBody;
    }

    public UpdateTenantConfigurationRequestBuilder setUpdateTenantConfigurationRequestBody(
        UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody) {
      this.updateTenantConfigurationRequestBody = updateTenantConfigurationRequestBody;
      return this;
    }

    public UpdateTenantConfigurationRequest build()
    {
      return new UpdateTenantConfigurationRequest(this);
    }
  }

}
