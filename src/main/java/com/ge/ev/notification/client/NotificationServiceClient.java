package com.ge.ev.notification.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ev.notification.NotificationService;
import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.domain.NotificationEvent;
import com.ge.ev.notification.client.domain.Tenant;
import com.ge.ev.notification.client.exceptions.NotificationClientException;
import com.ge.ev.notification.client.exceptions.RequestException;
import com.ge.ev.notification.client.requests.NotificationRequest;
import com.ge.ev.notification.client.requests.configuration.ConfigurationRequestBody;
import com.ge.ev.notification.client.requests.configuration.ConfigurationsRequest;
import com.ge.ev.notification.client.requests.configuration.CreateConfigurationRequest;
import com.ge.ev.notification.client.requests.configuration.DeleteConfigurationRequest;
import com.ge.ev.notification.client.requests.configuration.GetConfigurationsRequest;
import com.ge.ev.notification.client.requests.configuration.UpdateConfigurationRequest;
import com.ge.ev.notification.client.requests.email.SendEmailRequest;
import com.ge.ev.notification.client.requests.email.SendEmailRequestBody;
import com.ge.ev.notification.client.requests.event.GetEventsRequest;
import com.ge.ev.notification.client.requests.tenant.GetTenantRequest;
import com.ge.ev.notification.client.requests.tenant.TenantRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
import com.ge.ev.notification.client.response.NotificationServiceResponse;
import com.ge.ev.notification.client.response.SendEmailResponse;
import com.ge.ev.notification.status.NotificationServiceResponseStatus;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
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

  public NotificationServiceResponse sendRequest(NotificationRequest notificationRequest) throws RequestException, NotificationClientException
  {
    HttpClient client = HttpClientBuilder.create().build();
    NotificationServiceResponse notificationServiceResponse = null;
    HttpRequestBase requestBase = notificationRequest.getRequest();

    try {
      _logger.debug(requestBase.getURI().toURL().toString());
    } catch (MalformedURLException e) {
     throw new NotificationClientException("Request Url is malformed: " +  notificationRequest.getRequestUrl(), e.getMessage() );
    }

    HttpResponse response = null;
    try {
      response = client.execute(requestBase);
    } catch (IOException e) {
      throw new RequestException( "Client has encountered a problem with this request ", notificationRequest.getRequestUrl(), -1, e.getMessage() );
    }

    if (response != null) {

      _logger.debug( response.getStatusLine().getStatusCode() + ", " + response.getStatusLine().getReasonPhrase());

      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
      {
          HttpEntity entity = response.getEntity();
          if (entity != null) {
            BufferedInputStream bufferedInputStream = null;
            try
            {
              bufferedInputStream = new BufferedInputStream(response.getEntity().getContent());
              String json = IOUtils.toString(bufferedInputStream);
              notificationServiceResponse = mapper.readValue(json, NotificationServiceResponse.class);
              bufferedInputStream.close();
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }

            _logger.debug( notificationServiceResponse.toJson() );

            Long status = notificationServiceResponse.getStatus();

            if ( !status.equals(NotificationServiceResponseStatus.Ok.getValue()) &&
                !status.equals(NotificationServiceResponseStatus.NotificationEmailMessageQueued.getValue()) )
            {
              throw new RequestException(notificationServiceResponse.getMessage(), notificationRequest.getRequestUrl(), notificationServiceResponse.getStatus().intValue(), notificationServiceResponse.getMessage());
            }
          }
      }
      else
        {
        throw new RequestException("Service has returned error: ",  notificationRequest.getRequestUrl(), response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
      }
    }

    return notificationServiceResponse;
  }

  @Override
  public Tenant getTenant(String token) throws RequestException, NotificationClientException {

    GetTenantRequest getTenantRequest = new GetTenantRequest.GetTenantRequestBuilder(this.baseUrl, this.version, this.tenantUuid).
        setToken(token).
        build();

    return sendTenantRequest(getTenantRequest);
  }

  @Override
  public Tenant updateTenant(String token, UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody) throws RequestException, NotificationClientException{

    UpdateTenantConfigurationRequest updateTenantConfigurationRequest = new UpdateTenantConfigurationRequest.UpdateTenantConfigurationRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setUpdateTenantConfigurationRequestBody(updateTenantConfigurationRequestBody)
        .setToken(token)
        .build();

    return sendTenantRequest(updateTenantConfigurationRequest);
  }

  @Override
  public List<Configuration> getConfigurations(String token, String configurationUuid) throws RequestException, NotificationClientException{

    GetConfigurationsRequest getConfigurationsRequest = new GetConfigurationsRequest.GetConfigurationsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setConfigurationUuid(configurationUuid)
        .setToken(token)
        .build();

    return sendConfigurationRequest(getConfigurationsRequest);
  }

  @Override
  public List<Configuration> createConfiguration(String token, ConfigurationRequestBody configurationRequestBody) throws RequestException, NotificationClientException
  {
    CreateConfigurationRequest createConfigurationRequest = new CreateConfigurationRequest.CreateConfigurationRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .addConfigurationRequestBody(configurationRequestBody)
        .setToken(token)
        .build();

    if (createConfigurationRequest.getConfigurationRequestBodies() != null) {
      for (ConfigurationRequestBody configurationRequestBody1 : createConfigurationRequest.getConfigurationRequestBodies()) {
        _logger.debug(configurationRequestBody1.toJson());
      }
    }

    return sendConfigurationRequest(createConfigurationRequest);
  }

  @Override
  public List<Configuration> updateConfiguration(String token, String configurationUuid, ConfigurationRequestBody configurationRequestBody)  throws RequestException, NotificationClientException {
    UpdateConfigurationRequest updateConfigurationRequest = new UpdateConfigurationRequest.UpdateConfigurationRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setConfigurationUuid(configurationUuid)
        .setConfigurationRequestBody(configurationRequestBody)
        .setToken(token)
        .build();

    return sendConfigurationRequest(updateConfigurationRequest);
  }

  @Override
  public List<Configuration> deleteConfiguration(String token, String configurationUuid) throws RequestException, NotificationClientException {

    DeleteConfigurationRequest deleteConfigurationRequest = new DeleteConfigurationRequest.DeleteConfigurationsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setConfigurationUuid(configurationUuid)
        .setToken(token)
        .build();

    return sendConfigurationRequest(deleteConfigurationRequest);
  }

  @Override
  public SendEmailResponse sendEmail(String token, String configurationUuid, SendEmailRequestBody sendEmailRequestBody) throws RequestException, NotificationClientException {
     SendEmailResponse sendEmailResponse = null;
    if (sendEmailRequestBody != null) {
      _logger.debug(sendEmailRequestBody.toJson());
    }

    SendEmailRequest sendEmailRequest = new SendEmailRequest.SendEmailRequestBuilder(this.baseUrl, this.version, this.tenantUuid, configurationUuid)
        .setSendEmailRequestBody(sendEmailRequestBody)
        .setToken(token)
        .build();

    NotificationServiceResponse notificationServiceResponse = sendRequest(sendEmailRequest);
    _logger.debug(notificationServiceResponse.toJson());

    if (notificationServiceResponse != null)
    {
      sendEmailResponse = SendEmailResponse.toObject((LinkedHashMap) notificationServiceResponse.getPayload());
    }

    return sendEmailResponse;
  }

  @Override
  public List<NotificationEvent> getEvents(String token, String notificationReferenceUuid) throws RequestException, NotificationClientException {

    ArrayList<NotificationEvent> notificationEvents = new ArrayList<>();

    GetEventsRequest getEventsRequest = new GetEventsRequest.GetEventsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setNotificationReferenceUuid(notificationReferenceUuid)
        .setToken(token)
        .build();

    NotificationServiceResponse notificationServiceResponse = sendRequest(getEventsRequest);
    _logger.debug(notificationServiceResponse.toJson());

    if (notificationServiceResponse != null)
    {
      try {
        ArrayList<LinkedHashMap<String, Object>> hashMaps = (ArrayList<LinkedHashMap<String, Object>>) notificationServiceResponse.getPayload();
        if (hashMaps != null) {
          for (LinkedHashMap<String, Object> map : hashMaps) {
            notificationEvents.add(NotificationEvent.toObject(map));
          }
        }
      }
      catch (ClassCastException ex) {}
    }

    return notificationEvents;
  }

  private List<Configuration> sendConfigurationRequest(ConfigurationsRequest configurationsRequest) throws RequestException, NotificationClientException
  {
    ArrayList<Configuration> configurations = new ArrayList<>();


    if (configurationsRequest.getRequestBody() != null) {
      _logger.debug(configurationsRequest.getRequestBody().toJson());
    }

    NotificationServiceResponse notificationServiceResponse = sendRequest(configurationsRequest);

    _logger.debug(notificationServiceResponse.toJson());

    if (notificationServiceResponse != null)
    {
      try {
        ArrayList<LinkedHashMap<String, Object>> hashMaps = (ArrayList<LinkedHashMap<String, Object>>) notificationServiceResponse
            .getPayload();
        if (hashMaps != null) {
          for (LinkedHashMap<String, Object> map : hashMaps) {
            configurations.add(Configuration.toObject(map));
          }
        }
      }
      catch (ClassCastException ex) {}
      try{
        configurations.add(Configuration.toObject((LinkedHashMap) notificationServiceResponse.getPayload()));
      }
      catch (ClassCastException ex) {}
    }

    return configurations;
  }

  private Tenant sendTenantRequest(TenantRequest tenantRequest) throws RequestException, NotificationClientException {
    Tenant tenant = null;

    if (tenantRequest.getRequestBody() != null) {
      _logger.debug(tenantRequest.getRequestBody().toJson());
    }

    NotificationServiceResponse notificationServiceResponse = sendRequest(tenantRequest);

    _logger.debug(notificationServiceResponse.toJson());

    if (notificationServiceResponse != null)
    {
      tenant = Tenant.toObject((LinkedHashMap) notificationServiceResponse.getPayload());
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
