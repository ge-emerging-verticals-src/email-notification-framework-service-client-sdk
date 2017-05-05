package com.ge.ev.notification.client.requests.template;

import java.io.UnsupportedEncodingException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/4/17.
 */
public class CreateRecipientsRequest extends RecipientsRequest {

  private CreateRecipientsRequestBody createRecipientsRequestBody;

  protected CreateRecipientsRequest(CreateRecipientsRequestBuilder builder) {
    super(builder);

    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    HttpPost post= new HttpPost(getRequestUrl());
    this.createRecipientsRequestBody = builder.getCreateRecipientsRequestBody();

    try {
      post.setEntity(new StringEntity( this.createRecipientsRequestBody.toJson() ));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    super.requestBase = post;
  }

  public static class CreateRecipientsRequestBuilder extends RecipientsRequestBuilder
  {
    private CreateRecipientsRequestBody createRecipientsRequestBody;

    public CreateRecipientsRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public CreateRecipientsRequestBody getCreateRecipientsRequestBody() {
      return createRecipientsRequestBody;
    }

    public CreateRecipientsRequestBuilder setCreateRecipientsRequestBody(
        CreateRecipientsRequestBody createRecipientsRequestBody) {
      this.createRecipientsRequestBody = createRecipientsRequestBody;
      return this;
    }

    public CreateRecipientsRequestBuilder setMatcherUuid(String matcherUuid) {
      this.matcherUuid = matcherUuid;
      return this;
    }

    public CreateRecipientsRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public CreateRecipientsRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public CreateRecipientsRequestBuilder setRecipientUuid(String recipientUuid) {
      this.recipientUuid = recipientUuid;
      return this;
    }

    public CreateRecipientsRequest build()
    {
      return new CreateRecipientsRequest(this);
    }
  }

}
