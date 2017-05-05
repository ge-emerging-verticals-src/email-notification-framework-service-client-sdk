package com.ge.ev.notification.client.requests.configuration;

import com.ge.ev.notification.client.requests.NotificationRequestImpl;
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
    super(builder);
    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    this.configurationUuid = builder.getConfigurationUuid() != null ? builder.getConfigurationUuid() : "";
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
    protected String configurationUuid;

    public ConfigurationsRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public String getConfigurationUuid() { return configurationUuid; }

  }
}
