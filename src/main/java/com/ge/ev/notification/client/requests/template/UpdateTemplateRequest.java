package com.ge.ev.notification.client.requests.template;

import java.io.UnsupportedEncodingException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/4/17.
 */
public class UpdateTemplateRequest extends CreateTemplateRequest {

  protected UpdateTemplateRequest(UpdateTemplateRequestBuilder builder) {
    super(builder);

    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    HttpPatch patch= new HttpPatch(getRequestUrl());
    this.templateRequestBody = builder.getTemplateRequestBody();

    try {
      patch.setEntity(new StringEntity( this.templateRequestBody.toJson() ));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = patch;
  }

  public static class UpdateTemplateRequestBuilder extends CreateTemplateRequestBuilder
  {
    private TemplateRequestBody templateRequestBody;

    public UpdateTemplateRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public TemplateRequestBody getTemplateRequestBody() {
      return templateRequestBody;
    }

    public UpdateTemplateRequestBuilder setTemplateRequestBody(
        TemplateRequestBody templateRequestBody) {
      this.templateRequestBody = templateRequestBody;
      return this;
    }

    public UpdateTemplateRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public UpdateTemplateRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public UpdateTemplateRequest build()
    {
      return new UpdateTemplateRequest(this);
    }
  }

}
