package com.ge.ev.notification.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ev.notification.NotificationService;
import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.domain.Matcher;
import com.ge.ev.notification.client.domain.NotificationEvent;
import com.ge.ev.notification.client.domain.Recipient;
import com.ge.ev.notification.client.domain.Template;
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
import com.ge.ev.notification.client.requests.template.CreateMatchersRequest;
import com.ge.ev.notification.client.requests.template.CreateMatchersRequestBody;
import com.ge.ev.notification.client.requests.template.CreateRecipientsRequest;
import com.ge.ev.notification.client.requests.template.CreateRecipientsRequestBody;
import com.ge.ev.notification.client.requests.template.CreateTemplateRequest;
import com.ge.ev.notification.client.requests.template.CreateTemplateRequestBody;
import com.ge.ev.notification.client.requests.template.DeleteMatcherRequest;
import com.ge.ev.notification.client.requests.template.DeleteRecipientsRequest;
import com.ge.ev.notification.client.requests.template.DeleteTemplateRequest;
import com.ge.ev.notification.client.requests.template.GetMatcherRequest;
import com.ge.ev.notification.client.requests.template.GetRecipientsRequest;
import com.ge.ev.notification.client.requests.template.GetTemplateRequest;
import com.ge.ev.notification.client.requests.template.MatchersRequest;
import com.ge.ev.notification.client.requests.template.RecipientsRequest;
import com.ge.ev.notification.client.requests.template.TemplateRequest;
import com.ge.ev.notification.client.requests.template.UploadTemplateRequest;
import com.ge.ev.notification.client.requests.template.UploadTemplateRequestBody;
import com.ge.ev.notification.client.requests.tenant.GetTenantRequest;
import com.ge.ev.notification.client.requests.tenant.TenantRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
import com.ge.ev.notification.client.response.NotificationServiceResponse;
import com.ge.ev.notification.client.response.SendEmailResponse;
import com.ge.ev.notification.status.NotificationServiceResponseStatus;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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

  protected NotificationServiceResponse sendRequest(NotificationRequest notificationRequest) throws RequestException, NotificationClientException
  {
    HttpClient client = HttpClientBuilder.create().build();
    NotificationServiceResponse notificationServiceResponse = null;
    HttpRequestBase requestBase = notificationRequest.getRequest();

    try {
      _logger.debug(requestBase.getURI().toURL().toString());
    } catch (MalformedURLException e) {
     throw new NotificationClientException("Request Url is malformed: " +  notificationRequest.getRequestUrl(), e.getMessage() );
    }

    HttpResponse response;
    try {
      response = client.execute(requestBase);
    } catch (IOException e) {
      throw new RequestException( "Client has encountered a problem with this request ", "",  notificationRequest.getRequestUrl(), -1, e.getMessage() );
    }

    if (response != null)
    {
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
              throw new RequestException(notificationServiceResponse.getMessage(), notificationServiceResponse.getPayload().toString(), notificationRequest.getRequestUrl(), notificationServiceResponse.getStatus().intValue(), notificationServiceResponse.getMessage());
            }
          }
      }
      else
        {
          String details = null;
          try {
             details = IOUtils.toString(response.getEntity().getContent());
          } catch (IOException e) {
            e.printStackTrace();
          }

          throw new RequestException("Service has returned error: ",  details, notificationRequest.getRequestUrl(), response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
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
  public SendEmailResponse sendEmail(String token, String configurationUuid, SendEmailRequestBody sendEmailRequestBody, String templateUuid) throws RequestException, NotificationClientException {
     SendEmailResponse sendEmailResponse = null;
    if (sendEmailRequestBody != null) {
      _logger.debug(sendEmailRequestBody.toJson());
    }

    SendEmailRequest sendEmailRequest = new SendEmailRequest.SendEmailRequestBuilder(this.baseUrl, this.version, this.tenantUuid, configurationUuid)
        .setSendEmailRequestBody(sendEmailRequestBody)
        .setTemplateUuid(templateUuid)
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

  @Override
  public List<Template> getTemplates(String token, String templateUuid) throws RequestException, NotificationClientException {

    GetTemplateRequest getTemplateRequest = new GetTemplateRequest.GetTemplateRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateUuid(templateUuid)
        .setToken(token)
        .build();

    return  sendTemplateRequest(getTemplateRequest);
  }

  @Override
  public List<Template> createTemplate(String token, CreateTemplateRequestBody createTemplateRequestBody, InputStream templateFileInputStream) throws RequestException, NotificationClientException {
    CreateTemplateRequest createTemplateRequest = new CreateTemplateRequest.CreateTemplateRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setCreateTemplateRequestBody(createTemplateRequestBody)
        .setToken(token)
        .build();

     List<Template> templates =  sendTemplateRequest(createTemplateRequest);

    if (templates != null && templateFileInputStream != null) {
      UploadTemplateRequestBody uploadTemplateRequestBody = new UploadTemplateRequestBody.UploadTemplateRequestBodyBuilder(templateFileInputStream)
          .build();

      UploadTemplateRequest uploadTemplateRequest = new UploadTemplateRequest.UploadTemplateRequestBuilder(
          this.baseUrl, this.version, this.tenantUuid)
          .setTemplateUuid(templates.get(0).getTemplateUuid())
          .setUploadTemplateRequestBody(uploadTemplateRequestBody)
          .setToken(token)
          .build();

      templates = sendTemplateRequest(uploadTemplateRequest);
    }

    return templates;
  }

  @Override
  public List<Template> deleteTemplate(String token, String templateUuid) throws RequestException, NotificationClientException {

    DeleteTemplateRequest deleteTemplateRequest = new DeleteTemplateRequest.DeleteTemplateRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateUuid(templateUuid)
        .setToken(token)
        .build();

    return sendTemplateRequest(deleteTemplateRequest);
  }

  @Override
  public List<Matcher> getMatchers(String token, String templateUuid, String matcherUuid) throws RequestException, NotificationClientException {

    GetMatcherRequest getMatcherRequest = new GetMatcherRequest.GetMatcherRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setMatcherUuid(matcherUuid)
        .setTemplateUuid(templateUuid)
        .setToken(token)
        .build();

    return sendMatchersRequest(getMatcherRequest);

  }

  @Override
  public List<Matcher> createMatcher(String token, String templateUuid, CreateMatchersRequestBody createMatchersRequestBody) throws RequestException, NotificationClientException {

    CreateMatchersRequest createMatchersRequest = new CreateMatchersRequest.CreateMatchersRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setCreateMatchersRequestBody(createMatchersRequestBody)
        .setTemplateUuid(templateUuid)
        .setToken(token)
        .build();

    return sendMatchersRequest(createMatchersRequest);
  }

  @Override
  public List<Matcher> deleteMatcher(String token, String templateUuid, String matcherUuid) throws RequestException, NotificationClientException {

    DeleteMatcherRequest deleteMatcherRequest = new DeleteMatcherRequest.DeleteMatchersRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateUuid(templateUuid)
        .setMatcherUuid(matcherUuid)
        .setToken(token)
        .build();

    return sendMatchersRequest(deleteMatcherRequest);
  }

  @Override
  public List<Recipient> getRecipients(String token, String templateUuid, String matcherUuid, String recipientUuid) throws RequestException, NotificationClientException {
    GetRecipientsRequest getRecipientsRequest = new GetRecipientsRequest.GetRecipientsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setMatcherUuid(matcherUuid)
        .setTemplateUuid(templateUuid)
        .setRecipientUuid(recipientUuid)
        .setToken(token)
        .build();

    return sendRecipientsRequest(getRecipientsRequest);
  }

  @Override
  public List<Recipient> createRecipients(String token, String templateUuid, String matcherUuid, CreateRecipientsRequestBody createRecipientsRequestBody) throws RequestException, NotificationClientException {
    CreateRecipientsRequest createRecipientsRequest = new CreateRecipientsRequest.CreateRecipientsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setCreateRecipientsRequestBody(createRecipientsRequestBody)
        .setTemplateUuid(templateUuid)
        .setMatcherUuid(matcherUuid)
        .setToken(token)
        .build();

    return sendRecipientsRequest(createRecipientsRequest);
  }

  @Override
  public List<Recipient> deleteRecipient(String token, String templateUuid, String matcherUuid, String recipientUuid) throws RequestException, NotificationClientException {

    DeleteRecipientsRequest deleteRecipientsRequest = new DeleteRecipientsRequest.DeleteRecipientsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateUuid(templateUuid)
        .setMatcherUuid(matcherUuid)
        .setRecipientUuid(recipientUuid)
        .setToken(token)
        .build();

    return sendRecipientsRequest(deleteRecipientsRequest);
  }

  private List<Recipient> sendRecipientsRequest(RecipientsRequest recipientsRequest)throws RequestException, NotificationClientException
  {
    NotificationServiceResponse notificationServiceResponse = sendRequest(recipientsRequest);
    _logger.debug(notificationServiceResponse.toJson());

    ArrayList<Recipient> recipients = new ArrayList<>();
    if (notificationServiceResponse != null)
    {
      try {
        ArrayList<LinkedHashMap<String, Object>> hashMaps = (ArrayList<LinkedHashMap<String, Object>>) notificationServiceResponse.getPayload();
        if (hashMaps != null) {
          for (LinkedHashMap<String, Object> map : hashMaps) {
            recipients.add(Recipient.toObject(map));
          }
        }
      }
      catch (ClassCastException ex) {}
      try{
        recipients.add(Recipient.toObject((LinkedHashMap) notificationServiceResponse.getPayload()));
      }
      catch (ClassCastException ex) {}
    }
    return recipients;
  }

  private List<Matcher> sendMatchersRequest(MatchersRequest matchersRequest)throws RequestException, NotificationClientException
  {
    NotificationServiceResponse notificationServiceResponse = sendRequest(matchersRequest);
    _logger.debug(notificationServiceResponse.toJson());

    ArrayList<Matcher> matchers = new ArrayList<>();
    if (notificationServiceResponse != null)
    {
      try {
        ArrayList<LinkedHashMap<String, Object>> hashMaps = (ArrayList<LinkedHashMap<String, Object>>) notificationServiceResponse.getPayload();
        if (hashMaps != null) {
          for (LinkedHashMap<String, Object> map : hashMaps) {
            matchers.add(Matcher.toObject(map));
          }
        }
      }
      catch (ClassCastException ex) {}
      try{
        matchers.add(Matcher.toObject((LinkedHashMap) notificationServiceResponse.getPayload()));
      }
      catch (ClassCastException ex) {}
    }

    return matchers;
  }

  private List<Template> sendTemplateRequest(TemplateRequest templateRequest)throws RequestException, NotificationClientException
  {
    NotificationServiceResponse notificationServiceResponse = sendRequest(templateRequest);
    _logger.debug(notificationServiceResponse.toJson());

    ArrayList<Template> templates = new ArrayList<>();
    if (notificationServiceResponse != null)
    {
      try {
        ArrayList<LinkedHashMap<String, Object>> hashMaps = (ArrayList<LinkedHashMap<String, Object>>) notificationServiceResponse.getPayload();
        if (hashMaps != null) {
          for (LinkedHashMap<String, Object> map : hashMaps) {
            templates.add(Template.toObject(map));
          }
        }
      }
      catch (ClassCastException ex) {}
      try{
        templates.add(Template.toObject((LinkedHashMap) notificationServiceResponse.getPayload()));
      }
      catch (ClassCastException ex) {}
    }

    return templates;
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
