package com.ge.ev.notification.client.requests.configuration;

import com.ge.ev.notification.client.requests.NotificationRequestImpl;
import java.util.LinkedHashMap;
import org.apache.http.HttpHeaders;

/**
 * Created by 212391398 on 5/1/17.
 */
public class ConfigurationsRequest extends NotificationRequestImpl {

  protected static final String URL = "%s/%s%s";
  protected static final String URI = "/tenants/%s/configurations/%s";

  private String configurationUuid;

  protected ConfigurationsRequest(ConfigurationsRequestBuilder builder)
  {
    this.baseUrl = builder.getBaseUrl();
    this.version = builder.getVersion();
    this.tenantUuid = builder.getTenantUuid();
    this.token = builder.getToken();
    this.configurationUuid = builder.getConfigurationUuid() != null ? builder.getConfigurationUuid() : "";

    this.headers = new LinkedHashMap<>();
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    StringBuilder authString = new StringBuilder("bearer ");
    authString.append(this.token);
    this.headers.put(HttpHeaders.AUTHORIZATION, authString.toString());
  }

  @Override
  public String getRequestUri()
  {
    if (requestUri == null)
    {
      requestUri =  String.format(URI, tenantUuid, configurationUuid);
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

  protected static class ConfigurationsRequestBuilder extends NotificationRequestBuilder
  {
    protected String tenantUuid;
    protected String baseUrl;
    protected String version;
    protected String token;
    protected String configurationUuid;

    public ConfigurationsRequestBuilder() {}

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

    public String getConfigurationUuid() { return configurationUuid;}
  }
}
