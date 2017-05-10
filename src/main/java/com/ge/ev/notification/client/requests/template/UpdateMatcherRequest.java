package com.ge.ev.notification.client.requests.template;

import java.io.UnsupportedEncodingException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/10/17.
 */
public class UpdateMatcherRequest extends MatchersRequest {

  private MatchersRequestBody matchersRequestBody;

  protected UpdateMatcherRequest(UpdateMatcherRequestBuilder builder) {
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    matcherUuid = builder.getMatcherUuid();
    HttpPut put= new HttpPut(getRequestUrl());

    this.notificationRequestBody = this.matchersRequestBody = builder.getMatchersRequestBody();

    try {
      put.setEntity(new StringEntity( this.matchersRequestBody.toJson() ));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = put;
  }

  public static class UpdateMatcherRequestBuilder extends MatcherRequestBuilder
  {
    private MatchersRequestBody matchersRequestBody;

    public UpdateMatcherRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public MatchersRequestBody getMatchersRequestBody() {
      return matchersRequestBody;
    }

    public UpdateMatcherRequestBuilder setMatchersRequestBody(
        MatchersRequestBody matchersRequestBody) {
      this.matchersRequestBody = matchersRequestBody;
      return this;
    }

    public UpdateMatcherRequestBuilder setMatcherUuid(String matcherUuid) {
      this.matcherUuid = matcherUuid;
      return this;
    }

    public UpdateMatcherRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public UpdateMatcherRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public UpdateMatcherRequest build()
    {
      return new UpdateMatcherRequest(this);
    }
  }
}
