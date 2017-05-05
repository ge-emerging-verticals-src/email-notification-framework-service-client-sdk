package com.ge.ev.notification.client.requests.template;

import java.io.UnsupportedEncodingException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/4/17.
 */
public class CreateMatchersRequest extends MatchersRequest {

  private CreateMatchersRequestBody createMatchersRequestBody;

  protected CreateMatchersRequest(CreateMatchersRequestBuilder builder) {
    super(builder);

    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    HttpPost post= new HttpPost(getRequestUrl());
    this.createMatchersRequestBody = builder.getCreateMatchersRequestBody();

    try {
      post.setEntity(new StringEntity( this.createMatchersRequestBody.toJson() ));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = post;
  }

  public static class CreateMatchersRequestBuilder extends MatcherRequestBuilder
  {
    private CreateMatchersRequestBody createMatchersRequestBody;

    public CreateMatchersRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public CreateMatchersRequestBody getCreateMatchersRequestBody() {
      return createMatchersRequestBody;
    }

    public CreateMatchersRequestBuilder setCreateMatchersRequestBody(
        CreateMatchersRequestBody createMatchersRequestBody) {
      this.createMatchersRequestBody = createMatchersRequestBody;
      return this;
    }

    public CreateMatchersRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public CreateMatchersRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public CreateMatchersRequestBuilder setMatcherUuid(String matcherUuid) {
      this.matcherUuid = matcherUuid;
      return this;
    }

    public CreateMatchersRequest build()
    {
      return new CreateMatchersRequest(this);
    }
  }
}
