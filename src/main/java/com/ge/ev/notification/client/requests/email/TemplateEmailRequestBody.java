package com.ge.ev.notification.client.requests.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by 212391398 on 5/4/17.
 */
public class TemplateEmailRequestBody extends SendEmailRequestBody {

  private ObjectMapper objectMapper;
  private HashMap<String, Object> map;

  private TemplateEmailRequestBody(TemplateEmailRequestBodyBuilder builder) {
    super(builder);
    this.map = builder.getMap();
    objectMapper = new ObjectMapper();
  }

  public HashMap<String, Object> getMap() {
    return map;
  }

  @Override
  public String toJson()
  {
    String json = "{}";
    try {
      json = objectMapper.writeValueAsString(map);
    } catch (JsonProcessingException e) {}
    return json;
  }

  public static class TemplateEmailRequestBodyBuilder extends SendEmailRequestBodyBuilder
  {
    private LinkedHashMap<String, Object> map;

    public TemplateEmailRequestBodyBuilder()
    {
      map = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Object> getMap() {
      return map;
    }

    public TemplateEmailRequestBodyBuilder addKeyValue(String key, Object value) {
      map.put(key, value);
      return this;
    }

    public  TemplateEmailRequestBody build()
    {
      return new TemplateEmailRequestBody(this);
    }

  }


}
