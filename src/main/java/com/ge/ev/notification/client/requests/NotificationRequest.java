package com.ge.ev.notification.client.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by 212391398 on 4/18/17.
 */
public interface NotificationRequest {

  String getRequestUri();

  HttpRequestBase getRequest();

  NotificationRequestBody getRequestBody();

  String getBaseUrl();

  String  getVersion();

  String getToken();

  String getRequestUrl();

  String toJson() throws JsonProcessingException;

}
