package com.ge.ev.notification.client.requests.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;

/**
 * Created by 212391398 on 5/4/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateMatchersRequestBody extends JsonObject implements NotificationRequestBody  {

  private String matchers;

  private CreateMatchersRequestBody(CreateMatchersRequestBodyBuilder builder)
  {
   this.matchers = builder.getMatchers();
  }

  public String getMatchers() {
    return matchers;
  }

  public static class CreateMatchersRequestBodyBuilder
  {
    private String matchers;

    public CreateMatchersRequestBodyBuilder(String matchers)
    {
      this.matchers = matchers;
    }

    public String getMatchers() {
      return matchers;
    }

    public CreateMatchersRequestBody build()
    {
      return new CreateMatchersRequestBody(this);
    }

  }

}
