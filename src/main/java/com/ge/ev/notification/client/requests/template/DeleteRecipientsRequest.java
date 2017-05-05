package com.ge.ev.notification.client.requests.template;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;

/**
 * Created by 212391398 on 5/4/17.
 */
public class DeleteRecipientsRequest extends RecipientsRequest {

  protected DeleteRecipientsRequest(DeleteRecipientsRequestBuilder builder) {
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    super.requestBase = new HttpDelete(getRequestUrl());
  }

  public static class DeleteRecipientsRequestBuilder extends RecipientsRequestBuilder
  {
    public DeleteRecipientsRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public DeleteRecipientsRequestBuilder setMatcherUuid(String matcherUuid) {
      this.matcherUuid = matcherUuid;
      return this;
    }

    public DeleteRecipientsRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public DeleteRecipientsRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public DeleteRecipientsRequestBuilder setRecipientUuid(String recipientUuid) {
      this.recipientUuid = recipientUuid;
      return this;
    }

    public DeleteRecipientsRequest build()
    {
      return new DeleteRecipientsRequest(this);
    }
  }
}
