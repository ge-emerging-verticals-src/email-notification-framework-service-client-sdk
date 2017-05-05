package com.ge.ev.notification.client.requests.template;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;

/**
 * Created by 212391398 on 5/4/17.
 */
public class GetRecipientsRequest extends RecipientsRequest {

  protected GetRecipientsRequest(GetRecipientsRequestBuilder builder) {
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    recipientUuid = builder.getRecipientUuid();
    super.requestBase = new HttpGet(getRequestUrl());
  }

  public static class GetRecipientsRequestBuilder extends RecipientsRequestBuilder
  {
    public GetRecipientsRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public GetRecipientsRequestBuilder setMatcherUuid(String matcherUuid) {
      this.matcherUuid = matcherUuid;
      return this;
    }

    public GetRecipientsRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public GetRecipientsRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public GetRecipientsRequestBuilder setRecipientUuid(String recipientUuid) {
      this.recipientUuid = recipientUuid;
      return this;
    }

    public GetRecipientsRequest build()
    {
      return new GetRecipientsRequest(this);
    }
  }
}
