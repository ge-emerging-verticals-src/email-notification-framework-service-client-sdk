package com.ge.ev.notification.client.requests.configuration;

import com.ge.ev.notification.client.util.JsonSerializableArrayList;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/2/17.
 */
public class CreateConfigurationRequest extends ConfigurationsRequest {

  JsonSerializableArrayList<ConfigurationRequestBody> configurationRequestBodies;

  public JsonSerializableArrayList<ConfigurationRequestBody> getConfigurationRequestBodies() {
    return configurationRequestBodies;
  }

  protected CreateConfigurationRequest(CreateConfigurationRequestBuilder builder) {
    super(builder);

    HttpPost post = new HttpPost(getRequestUrl());
    this.configurationRequestBodies = builder.getConfigurationRequestBodies();
    try {
      post.setEntity(new StringEntity( this.configurationRequestBodies.toJson() ));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = post;
  }

  public static class CreateConfigurationRequestBuilder extends ConfigurationsRequestBuilder
  {
    protected JsonSerializableArrayList<ConfigurationRequestBody> configurationRequestBodies;

    public CreateConfigurationRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
      configurationRequestBodies = new JsonSerializableArrayList<>();
    }

    public JsonSerializableArrayList<ConfigurationRequestBody> getConfigurationRequestBodies() {
      return configurationRequestBodies;
    }

    public CreateConfigurationRequestBuilder addConfigurationRequestBody(ConfigurationRequestBody configurationRequestBody) {
      this.configurationRequestBodies.add(configurationRequestBody);
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
