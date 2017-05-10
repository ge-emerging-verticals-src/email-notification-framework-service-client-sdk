package com.ge.ev.notification.client.requests.template;

import java.io.UnsupportedEncodingException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/3/17.
 */
public class CreateTemplateRequest extends TemplateRequest{

  protected TemplateRequestBody templateRequestBody;

  protected CreateTemplateRequest(CreateTemplateRequestBuilder builder) {
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    HttpPost post= new HttpPost(getRequestUrl());
    this.notificationRequestBody =  this.templateRequestBody = builder.getTemplateRequestBody();

    try {
      post.setEntity(new StringEntity( this.templateRequestBody.toJson() ));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = post;
  }

  public static class CreateTemplateRequestBuilder extends TemplateRequestBuilder
  {
    protected TemplateRequestBody templateRequestBody;

    public CreateTemplateRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public TemplateRequestBody getTemplateRequestBody() {
      return templateRequestBody;
    }

    public CreateTemplateRequestBuilder setTemplateRequestBody(
        TemplateRequestBody templateRequestBody) {
      this.templateRequestBody = templateRequestBody;
      return this;
    }

    public CreateTemplateRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public CreateTemplateRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public CreateTemplateRequest build()
    {
      return new CreateTemplateRequest(this);
    }
  }
}
