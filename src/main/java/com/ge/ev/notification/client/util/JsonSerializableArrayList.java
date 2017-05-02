package com.ge.ev.notification.client.util;

import com.ge.ev.notification.client.json.JsonObject;
import java.util.ArrayList;

/**
 * Created by 212391398 on 5/2/17.
 */
public class JsonSerializableArrayList<T extends JsonObject> extends ArrayList<T> {
  public String toJson()
  {
   StringBuilder json = new StringBuilder("[");

   if (this.size() > 0) {
     int last = this.size();
     int count = 0;
     for (JsonObject object : this.subList(0, last)) {
       json.append(object.toJson());
       count++;
       if (count < last)
         json.append(",");
     }
   }

   json.append("]");

   return json.toString();
  }
}
