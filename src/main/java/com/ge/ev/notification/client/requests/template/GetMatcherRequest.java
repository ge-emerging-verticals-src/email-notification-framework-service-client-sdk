package com.ge.ev.notification.client.requests.template;


import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;

/**
 * Created by 212391398 on 5/4/17.
 */
public class GetMatcherRequest extends MatchersRequest
{
  protected GetMatcherRequest(GetMatcherRequestBuilder builder) {
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    matcherUuid = builder.getMatcherUuid();
    super.requestBase = new HttpGet(getRequestUrl());
  }

  public static class GetMatcherRequestBuilder extends MatcherRequestBuilder
  {
    public GetMatcherRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public GetMatcherRequestBuilder setMatcherUuid(String matcherUuid) {
      this.matcherUuid = matcherUuid;
      return this;
    }

    public GetMatcherRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public GetMatcherRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public GetMatcherRequest build()
    {
      return new GetMatcherRequest(this);
    }
  }
}
