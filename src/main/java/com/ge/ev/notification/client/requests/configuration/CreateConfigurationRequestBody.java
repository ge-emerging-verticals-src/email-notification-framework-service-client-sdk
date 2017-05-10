package com.ge.ev.notification.client.requests.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;
import com.ge.ev.notification.client.util.JsonSerializableArrayList;

/**
 * Created by 212391398 on 5/1/17.
 */
@JsonInclude(Include.NON_NULL)
public class CreateConfigurationRequestBody extends JsonObject implements NotificationRequestBody {

  JsonSerializableArrayList<Configuration> configurations;

  private CreateConfigurationRequestBody(CreateConfigurationRequestBodyBuilder configurationRequestBodyBuilder)
  {
      configurations = configurationRequestBodyBuilder.getConfigurations();
  }

  public JsonSerializableArrayList<Configuration> getConfigurations() {
    return configurations;
  }

  @Override
  public String toJson()
  {
    return configurations.toJson();
  }

  public static class CreateConfigurationRequestBodyBuilder
  {
    private JsonSerializableArrayList<Configuration> configurations;

    public CreateConfigurationRequestBodyBuilder() {
      configurations = new JsonSerializableArrayList<>();
    }

    public JsonSerializableArrayList<Configuration> getConfigurations() {
      return configurations;
    }

    public CreateConfigurationRequestBodyBuilder addConfigurations(Configuration configuration) {
      this.configurations.add(configuration);
      return this;
    }

    public CreateConfigurationRequestBody build()
    {
      return new CreateConfigurationRequestBody(this);
    }
  }
}
