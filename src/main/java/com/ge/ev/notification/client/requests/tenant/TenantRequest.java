package com.ge.ev.notification.client.requests.tenant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ev.notification.client.requests.NotificationRequest;
import com.ge.ev.notification.client.requests.NotificationRequestBody;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Created by 212391398 on 4/19/17.
 */
public class TenantRequest implements NotificationRequest {

  protected static final ObjectMapper mapper = new ObjectMapper();

  private static final String URL = "%s/%s%s";
  private static final String URI = "/tenants/%s";

  private String tenantUuid;

  private String baseUrl;

  private String version;

  private String requestUri;

  private String requestUrl;

  private String token;

  protected NotificationRequestBody notificationRequestBody;

  protected HttpRequestBase requestBase;

  protected TenantRequest(TenantRequestBuilder builder)
  {
    this.baseUrl = builder.getBaseUrl();
    this.version = builder.getVersion();
    this.tenantUuid = builder.getTenantUuid();
    this.token = builder.getToken();
  }

  public String getTenantUuid() {
    return this.tenantUuid;
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
  public HttpRequestBase getRequest() {

    requestBase.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    requestBase.setHeader(HttpHeaders.AUTHORIZATION, "bearer " + this.token);

    return requestBase;
  }

  @Override
  public NotificationRequestBody getRequestBody() {
    return notificationRequestBody;
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
  public String getRequestUrl() {
    if (requestUrl == null)
    {
      requestUrl =  String.format(URL, baseUrl, version, getRequestUri());
    }
    return requestUrl;
  }

  @Override
  public String toJson() throws JsonProcessingException {
    return this.mapper.writeValueAsString(this);
  }

  protected static class TenantRequestBuilder
  {
    protected String tenantUuid;
    protected String baseUrl;
    protected String version;
    protected String token;

    public TenantRequestBuilder() {}

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
