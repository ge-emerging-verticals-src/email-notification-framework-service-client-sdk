package com.ge.ev.notification.client.requests.tenant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ev.notification.client.requests.NotificationRequestBody;

/**
 * Created by 212391398 on 4/18/17.
 */
public class UpdateTenantConfigurationRequestBody implements NotificationRequestBody {
  
  private static final ObjectMapper mapper = new ObjectMapper();

  private String successWebhook;

  private String failWebhook;

  private UpdateTenantConfigurationRequestBody(UpdateTenantConfigurationRequestBodyBuilder builder)
  {
      this.failWebhook = builder.failWebhook;
      this.successWebhook = builder.successWebhook;
  }

  public String getSuccessWebhook() {
    return successWebhook;
  }

  public String getFailWebhook() {
    return failWebhook;
  }

  @Override
  public String toJson() throws JsonProcessingException {
    return mapper.writeValueAsString(this);
  }

  public static class UpdateTenantConfigurationRequestBodyBuilder
  {
    private String successWebhook;

    private String failWebhook;

    public UpdateTenantConfigurationRequestBodyBuilder() {}

    public String getSuccessWebhook() {
      return successWebhook;
    }

    public UpdateTenantConfigurationRequestBodyBuilder setSuccessWebhook(String successWebhook) {
      this.successWebhook = successWebhook;
      return this;
    }

    public String getFailWebhook() {
      return failWebhook;
    }

    public UpdateTenantConfigurationRequestBodyBuilder setFailWebhook(String failWebhook) {
      this.failWebhook = failWebhook;
      return this;
    }

    public UpdateTenantConfigurationRequestBody build()
    {
      return new UpdateTenantConfigurationRequestBody(this);
    }

  }
}
