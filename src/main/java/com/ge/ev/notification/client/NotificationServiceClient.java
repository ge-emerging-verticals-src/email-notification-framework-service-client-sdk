package com.ge.ev.notification.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ev.notification.NotificationService;
import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.domain.Tenant;
import com.ge.ev.notification.client.requests.NotificationRequest;
import com.ge.ev.notification.client.requests.configuration.GetConfigurationsRequest;
import com.ge.ev.notification.client.requests.tenant.GetTenantRequest;
import com.ge.ev.notification.client.requests.tenant.TenantRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
import com.ge.ev.notification.client.response.NotificationServiceResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 212391398 on 4/18/17.
 */
public class NotificationServiceClient implements NotificationService {

  private static final Logger _logger = LoggerFactory.getLogger(NotificationServiceClient.class);

  private static final ObjectMapper mapper = new ObjectMapper();

  private String tenantUuid = null;
  private String version = null;
  private String baseUrl = null;

  private NotificationServiceClient(NotificationServiceClientBuilder notificationServiceClientBuilder)
  {
    this.baseUrl = notificationServiceClientBuilder.getBaseUrl();
    this.version = notificationServiceClientBuilder.getVersion();
    this.tenantUuid = notificationServiceClientBuilder.getTenantUuid();
  }

  public String getTenantUuid() {
    return tenantUuid;
  }

  public String getVersion() {
    return version;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public NotificationServiceResponse sendRequest(NotificationRequest notificationRequest) throws IOException
  {
    HttpClient client = HttpClientBuilder.create().build();
    NotificationServiceResponse notificationServiceResponse = null;
    try {
      HttpRequestBase requestBase = notificationRequest.getRequest();

      _logger.debug(requestBase.getURI().toURL().toString());

      HttpResponse response = client.execute(requestBase);

      if (response != null)
      {
        HttpEntity entity = response.getEntity();
        if (entity != null)
        {
          BufferedInputStream bufferedInputStream = new BufferedInputStream(response.getEntity().getContent());
          String json = IOUtils.toString(bufferedInputStream);
          notificationServiceResponse = mapper.readValue(json, NotificationServiceResponse.class);
          bufferedInputStream.close();
        }
      }
    } catch (Exception ex){
      ex.printStackTrace();
    }

    return notificationServiceResponse;
  }

  @Override
  public Tenant getTenant(String token) throws IOException {

    GetTenantRequest getTenantRequest = new GetTenantRequest.GetTenantRequestBuilder(this.baseUrl, this.version, this.tenantUuid).
        setToken(token).
        build();

    return sendTenantRequest(getTenantRequest);
  }

  @Override
  public Tenant updateTenant(String token, UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody) throws IOException {

    UpdateTenantConfigurationRequest updateTenantConfigurationRequest = new UpdateTenantConfigurationRequest.UpdateTenantConfigurationRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setUpdateTenantConfigurationRequestBody(updateTenantConfigurationRequestBody)
        .setToken(token)
        .build();

    return sendTenantRequest(updateTenantConfigurationRequest);
  }

  @Override
  public List<Configuration> getConfigurations(String token, String configurationUuid) throws IOException {

    ArrayList<Configuration> configurations = new ArrayList<>();

    GetConfigurationsRequest configurationsRequest = new GetConfigurationsRequest.GetConfigurationsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setConfigurationUuid(configurationUuid)
        .setToken(token)
        .build();

    NotificationServiceResponse notificationServiceResponse = sendRequest(configurationsRequest);

    _logger.debug(notificationServiceResponse.toJson());

    if (notificationServiceResponse != null)
    {
      ArrayList<LinkedHashMap<String,Object>> payload = (ArrayList<LinkedHashMap<String, Object>>) notificationServiceResponse.getPayload();

      for (LinkedHashMap<String, Object> map : payload)
      {
        configurations.add(Configuration.toObject(map, Configuration.class));
      }
    }

    return configurations;
  }

  @Override
  public List<Configuration> createConfiguration(String token) {
    return null;
  }

  @Override
  public List<Configuration> updateConfiguration(String token) {
    return null;
  }

  @Override
  public Configuration deleteConfiguration(String token) {
    return null;
  }

  private Tenant sendTenantRequest(TenantRequest tenantRequest) throws IOException {
    Tenant tenant = null;

    NotificationServiceResponse notificationServiceResponse = sendRequest(tenantRequest);

    _logger.debug(notificationServiceResponse.toJson());

    if (notificationServiceResponse != null)
    {
      tenant = Tenant.toObject((LinkedHashMap) notificationServiceResponse.getPayload(), Tenant.class);
    }

    return tenant;
  }

  public static class NotificationServiceClientBuilder
  {
    protected String tenantUuid;
    protected String baseUrl;
    protected String version;

    public NotificationServiceClientBuilder() {}

    public String getTenantUuid() {
      return tenantUuid;
    }

    public String getBaseUrl() {
      return baseUrl;
    }

    public String getVersion() {
      return version;
    }

    public NotificationServiceClientBuilder setTenantUuid(String tenantUuid) {
      this.tenantUuid = tenantUuid;
      return this;
    }

    public NotificationServiceClientBuilder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public NotificationServiceClientBuilder setVersion(String version) {
      this.version = version;
      return this;
    }

    public NotificationServiceClient build()
    {
      return new NotificationServiceClient(this);
    }
  }
}
