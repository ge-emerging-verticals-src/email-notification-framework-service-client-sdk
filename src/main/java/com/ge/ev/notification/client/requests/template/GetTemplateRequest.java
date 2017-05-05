package com.ge.ev.notification.client.requests.template;

import org.apache.http.client.methods.HttpGet;

public class GetTemplateRequest extends TemplateRequest {

  protected GetTemplateRequest(GetTemplateRequestBuilder getTemplatesRequestBuilder) {
    super(getTemplatesRequestBuilder);
    super.requestBase = new HttpGet(getRequestUrl());
  }

  public static class GetTemplateRequestBuilder extends TemplateRequestBuilder
  {
    public GetTemplateRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public GetTemplateRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public GetTemplateRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public GetTemplateRequest build()
    {
      return new GetTemplateRequest(this);
    }
  }

}
