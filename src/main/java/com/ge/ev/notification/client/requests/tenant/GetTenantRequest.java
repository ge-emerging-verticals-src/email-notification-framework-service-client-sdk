package com.ge.ev.notification.client.requests.tenant;

import org.apache.http.client.methods.HttpGet;

/**
 * Created by 212391398 on 4/18/17.
 */
public class GetTenantRequest extends TenantRequest {

  private GetTenantRequest(GetTenantRequestBuilder builder)
  {
    super(builder);
    super.requestBase = new HttpGet(getRequestUrl());
  }

  public static class GetTenantRequestBuilder extends NotificationRequestBuilder
  {
    public GetTenantRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public GetTenantRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public GetTenantRequest build()
    {
       return new GetTenantRequest(this);
    }
  }
  
}
