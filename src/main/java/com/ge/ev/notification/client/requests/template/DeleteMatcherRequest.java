package com.ge.ev.notification.client.requests.template;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;

/**
 * Created by 212391398 on 5/4/17.
 */
public class DeleteMatcherRequest extends MatchersRequest {

  protected DeleteMatcherRequest(MatcherRequestBuilder builder) {
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    super.requestBase = new HttpDelete(getRequestUrl());
  }

  public static class DeleteMatchersRequestBuilder extends MatcherRequestBuilder
  {
    public DeleteMatchersRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public DeleteMatchersRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public DeleteMatchersRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public DeleteMatchersRequestBuilder setMatcherUuid(String matcherUuid) {
      this.matcherUuid = matcherUuid;
      return this;
    }

    public DeleteMatcherRequest build()
    {
      return new DeleteMatcherRequest(this);
    }
  }
}
