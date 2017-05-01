package com.ge.ev.notification.client.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by 212391398 on 5/1/17.
 */
public class JsonObject implements Serializable {
   private static final ObjectMapper mapper = new ObjectMapper();

   public String toJson()
   {
      try {
         return mapper.writeValueAsString(this);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }
      return "{}";
   }

   public static <T> T toObject(String json, Class<T> clazz)
   {
      try {
         return mapper.readValue(json, clazz);
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }

   public static <T> T toObject(Map<String, Object> map, Class<T> clazz)
   {
      try {
         return mapper.readValue(mapper.writeValueAsString(map), clazz);
      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }
}
