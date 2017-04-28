package com.ge.ev.notification.client.requests;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by 212391398 on 4/18/17.
 */
public interface NotificationRequestBody {
  String toJson() throws JsonProcessingException;
}
