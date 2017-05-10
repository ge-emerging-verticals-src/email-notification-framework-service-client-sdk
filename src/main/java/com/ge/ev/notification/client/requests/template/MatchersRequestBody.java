package com.ge.ev.notification.client.requests.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;

/**
 * Created by 212391398 on 5/4/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchersRequestBody extends JsonObject implements NotificationRequestBody  {

  private String matchers;

  private MatchersRequestBody(MatchersRequestBodyBuilder builder)
  {
   this.matchers = builder.getMatchers();
  }

  public String getMatchers() {
    return matchers;
  }

  public static class MatchersRequestBodyBuilder
  {
    private String matchers;

    public MatchersRequestBodyBuilder(String matchers)
    {
      this.matchers = matchers;
    }

    public String getMatchers() {
      return matchers;
    }

    public MatchersRequestBody build()
    {
      return new MatchersRequestBody(this);
    }
  }

}
