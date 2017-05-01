package com.ge.ev.notification.client.requests;

import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by 212391398 on 5/1/17.
 */
public class NotificationRequestImpl implements NotificationRequest{

  protected static final String URL = "%s/%s%s";
  protected static final String URI = "/tenants/%s";
  protected static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

  protected String tenantUuid;
  protected String baseUrl;
  protected String version;
  protected String requestUri;
  protected String requestUrl;
  protected String token;
  protected Map<String, String> headers;
  protected NotificationRequestBody notificationRequestBody;
  protected HttpRequestBase requestBase;

  @Override
  public String getTenantUuid() {
    return this.tenantUuid;
  }

  @Override
  public String getBaseUrl() {
    return this.baseUrl;
  }

  @Override
  public String getVersion() {
    return this.version;
  }

  @Override
  public String getToken() {
    return this.token;
  }

  @Override
  public HttpRequestBase getRequest() {

    for (String header : this.headers.keySet())
    {
      requestBase.setHeader(header, this.headers.get(header));
    }

    return requestBase;
  }

  @Override
  public NotificationRequestBody getRequestBody() {
    return notificationRequestBody;
  }

  @Override
  public Map<String, String> getHeaders() {
    return headers;
  }

  @Override
  public String getRequestUri()
  {
    if (requestUri == null)
    {
      requestUri =  String.format(URI, tenantUuid);
    }
    return requestUri;
  }

  @Override
  public String getRequestUrl() {
    if (requestUrl == null)
    {
      requestUrl =  String.format(URL, baseUrl, version, getRequestUri());
    }
    return requestUrl;
  }

  protected static class NotificationRequestBuilder
  {
    protected String tenantUuid;
    protected String baseUrl;
    protected String version;
    protected String token;

    public NotificationRequestBuilder() {}

    public String getTenantUuid() {
      return tenantUuid;
    }

    public String getBaseUrl() {
      return baseUrl;
    }

    public String getVersion() {
      return version;
    }

    public String getToken() {
      return token;
    }
  }

}
