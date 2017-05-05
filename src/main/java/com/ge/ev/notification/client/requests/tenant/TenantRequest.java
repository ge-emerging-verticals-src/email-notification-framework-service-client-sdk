package com.ge.ev.notification.client.requests.tenant;

import com.ge.ev.notification.client.requests.NotificationRequestImpl;
import org.apache.http.HttpHeaders;

/**
 * Created by 212391398 on 4/19/17.
 */
public class TenantRequest extends NotificationRequestImpl
{
  protected TenantRequest(NotificationRequestBuilder builder)
  {
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
  }
}
