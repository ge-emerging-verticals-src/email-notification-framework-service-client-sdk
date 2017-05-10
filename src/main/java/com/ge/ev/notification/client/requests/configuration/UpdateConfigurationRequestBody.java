package com.ge.ev.notification.client.requests.configuration;

import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;

/**
 * Created by 212391398 on 5/8/17.
 */
public class UpdateConfigurationRequestBody extends JsonObject implements NotificationRequestBody {
  Configuration configuration;

  private UpdateConfigurationRequestBody(UpdateConfigurationRequestBodyBuilder builder)
  {
    configuration = builder.getConfiguration();
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  @Override
  public String toJson()
  {
    return configuration.toJson();
  }

  public static class UpdateConfigurationRequestBodyBuilder
  {
    private Configuration configuration;

    public UpdateConfigurationRequestBodyBuilder() { }


    public Configuration getConfiguration() {
      return configuration;
    }

    public UpdateConfigurationRequestBodyBuilder setConfiguration(Configuration configuration) {
      this.configuration = configuration;
      return this;
    }

    public UpdateConfigurationRequestBody build()
    {
      return new UpdateConfigurationRequestBody(this);
    }
  }

}
