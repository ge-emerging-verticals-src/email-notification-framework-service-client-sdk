package com.ge.ev.notification.client.requests.configuration;

import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/2/17.
 */
public class CreateConfigurationRequest extends ConfigurationsRequest {

  CreateConfigurationRequestBody createConfigurationRequestBody;

  protected CreateConfigurationRequest(CreateConfigurationRequestBuilder builder) {
    super(builder);

    HttpPost post = new HttpPost(getRequestUrl());
     this.notificationRequestBody =  this.createConfigurationRequestBody = builder.getCreateConfigurationRequestBody();
    try {
      post.setEntity(new StringEntity( this.createConfigurationRequestBody.toJson() ));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = post;
  }

  public CreateConfigurationRequestBody getCreateConfigurationRequestBody() {
    return createConfigurationRequestBody;
  }

  public static class CreateConfigurationRequestBuilder extends ConfigurationsRequestBuilder
  {
    protected CreateConfigurationRequestBody createConfigurationRequestBody;

    public CreateConfigurationRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public CreateConfigurationRequestBody getCreateConfigurationRequestBody() {
      return createConfigurationRequestBody;
    }

    public CreateConfigurationRequestBuilder setCreateConfigurationRequestBody(CreateConfigurationRequestBody createConfigurationRequestBody) {
      this.createConfigurationRequestBody = createConfigurationRequestBody;
      return this;
    }

    public CreateConfigurationRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }
    
    public CreateConfigurationRequest build()
    {
      return new CreateConfigurationRequest(this);
    }
  }
}
