package com.ge.ev.notification.client.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonObject implements Serializable {

   private static final Logger _logger = LoggerFactory.getLogger(JsonObject.class);
   private static final ObjectMapper mapper = new ObjectMapper();

   public String toJson()
   {
      try {
         return mapper.writeValueAsString(this);
      } catch (JsonProcessingException e) {
         _logger.error(e.getMessage());
      }
      return "{}";
   }

   public static <T> T toObject(String json, Class<T> clazz)
   {
      try {
         return mapper.readValue(json, clazz);
      } catch (IOException e) {
         _logger.error(e.getMessage());
      }
      return null;
   }

   public static <T> T toObject(Map<String, Object> map, Class<T> clazz)
   {
      try {
         return mapper.readValue(mapper.writeValueAsString(map), clazz);
      } catch (IOException e) {
         _logger.error(e.getMessage());
      }
      return null;
   }
}
