package com.ge.ev.notification.client.requests;

import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by 212391398 on 4/18/17.
 */
public interface NotificationRequest {

  /**
   * 
   * @return
   */
  String getTenantUuid();

  /**
   *
   * @return
   */
  HttpRequestBase getRequest();

  /**
   *
   * @return
   */
  NotificationRequestBody getRequestBody();

  /**
   *
   * @return
   */
  String  getVersion();

  /**
   *
   * @return
   */
  String getToken();

  /**
   *
   * @return
   */
  String getBaseUrl();

  /**
   *
   * @return
   */
  String getRequestUri();

  /**
   *
   * @return
   */
  String getRequestUrl();

  /**
   *
   * @return
   */
  Map<String, String> getHeaders();
}
