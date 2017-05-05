package com.ge.ev.notification.client.requests.template;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;

/**
 * Created by 212391398 on 5/3/17.
 */
public class DeleteTemplateRequest extends TemplateRequest {
  protected DeleteTemplateRequest(DeleteTemplateRequestBuilder builder) {
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    super.requestBase = new HttpDelete(getRequestUrl());
  }

  public static class DeleteTemplateRequestBuilder extends TemplateRequestBuilder
  {
    public DeleteTemplateRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public DeleteTemplateRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public DeleteTemplateRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public DeleteTemplateRequest build()
    {
      return new DeleteTemplateRequest(this);
    }
  }
}
