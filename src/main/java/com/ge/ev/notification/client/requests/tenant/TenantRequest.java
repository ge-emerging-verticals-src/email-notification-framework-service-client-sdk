package com.ge.ev.notification.client.requests.tenant;

import com.ge.ev.notification.client.requests.NotificationRequestImpl;
import java.util.LinkedHashMap;
import org.apache.http.HttpHeaders;

/**
 * Created by 212391398 on 4/19/17.
 */
public class TenantRequest extends NotificationRequestImpl
{
  protected TenantRequest(NotificationRequestBuilder builder)
  {
    this.baseUrl = builder.getBaseUrl();
    this.version = builder.getVersion();
    this.tenantUuid = builder.getTenantUuid();
    this.token = builder.getToken();
    this.headers = new LinkedHashMap<>();

    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    StringBuilder authString = new StringBuilder("bearer ");
    authString.append(this.token);
    this.headers.put(HttpHeaders.AUTHORIZATION, authString.toString());
  }
}
