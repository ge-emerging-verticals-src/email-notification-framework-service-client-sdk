package com.ge.ev.notification.client.requests;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by 212391398 on 5/1/17.
 */
public class NotificationRequestImpl implements NotificationRequest {

  protected static final String URL = "%s/%s%s";
  protected static final String URI = "/tenants/%s";
  protected static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
  protected static final String CONTENT_TYPE_X_WWW_FORM_ENCODED = " application/x-www-form-urlencoded";
  protected static final String CONTENT_TYPE_MULTIPART_FORM_DATA =  "multipart/form-data";
  protected String tenantUuid;
  protected String baseUrl;
  protected String version;
  protected String requestUri;
  protected String requestUrl;
  protected String token;
  protected Map<String, String> headers;
  protected NotificationRequestBody notificationRequestBody;
  protected HttpRequestBase requestBase;

  protected NotificationRequestImpl(NotificationRequestBuilder notificationRequestBuilder)
  {
    this.baseUrl = notificationRequestBuilder.getBaseUrl();
    this.version = notificationRequestBuilder.getVersion();
    this.tenantUuid = notificationRequestBuilder.getTenantUuid();
    this.token = notificationRequestBuilder.getToken();

    this.headers = new LinkedHashMap<>();
    StringBuilder authString = new StringBuilder("bearer ");
    authString.append(this.token);
    this.headers.put(HttpHeaders.AUTHORIZATION, authString.toString());
  }

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

    public NotificationRequestBuilder( String baseUrl, String version, String tenantUuid  )
    {
      setBaseUrl(baseUrl);
      setVersion(version);
      setTenantUuid(tenantUuid);
    }

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

    protected NotificationRequestBuilder setTenantUuid(String tenantUuid) {
      this.tenantUuid = tenantUuid;
      return this;
    }

    protected NotificationRequestBuilder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    protected NotificationRequestBuilder setVersion(String version) {
      this.version = version;
      return this;
    }
  }

}
