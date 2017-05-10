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
import com.ge.ev.notification.client.requests.configuration.CreateConfigurationRequestBody;
import com.ge.ev.notification.client.requests.configuration.ConfigurationsRequest;
import com.ge.ev.notification.client.requests.configuration.CreateConfigurationRequest;
import com.ge.ev.notification.client.requests.configuration.DeleteConfigurationRequest;
import com.ge.ev.notification.client.requests.configuration.GetConfigurationsRequest;
import com.ge.ev.notification.client.requests.configuration.UpdateConfigurationRequest;
import com.ge.ev.notification.client.requests.configuration.UpdateConfigurationRequestBody;
import com.ge.ev.notification.client.requests.email.SendEmailRequest;
import com.ge.ev.notification.client.requests.email.SendEmailRequestBody;
import com.ge.ev.notification.client.requests.email.SendTemplateEmailRequestBody;
import com.ge.ev.notification.client.requests.event.GetEventsRequest;
import com.ge.ev.notification.client.requests.template.CreateMatchersRequest;
import com.ge.ev.notification.client.requests.template.MatchersRequestBody;
import com.ge.ev.notification.client.requests.template.CreateRecipientsRequest;
import com.ge.ev.notification.client.requests.template.CreateRecipientsRequestBody;
import com.ge.ev.notification.client.requests.template.CreateTemplateRequest;
import com.ge.ev.notification.client.requests.template.DeleteMatcherRequest;
import com.ge.ev.notification.client.requests.template.DeleteRecipientsRequest;
import com.ge.ev.notification.client.requests.template.DeleteTemplateRequest;
import com.ge.ev.notification.client.requests.template.GetMatcherRequest;
import com.ge.ev.notification.client.requests.template.GetRecipientsRequest;
import com.ge.ev.notification.client.requests.template.GetTemplateRequest;
import com.ge.ev.notification.client.requests.template.MatchersRequest;
import com.ge.ev.notification.client.requests.template.RecipientsRequest;
import com.ge.ev.notification.client.requests.template.TemplateRequest;
import com.ge.ev.notification.client.requests.template.TemplateRequestBody;
import com.ge.ev.notification.client.requests.template.UploadTemplateRequest;
import com.ge.ev.notification.client.requests.template.UploadTemplateRequestBody;
import com.ge.ev.notification.client.requests.tenant.GetTenantRequest;
import com.ge.ev.notification.client.requests.tenant.TenantRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
import com.ge.ev.notification.client.response.NotificationServiceResponse;
import com.ge.ev.notification.client.response.SendEmailResponse;
import com.ge.ev.notification.status.NotificationServiceResponseStatus;
import com.ge.ev.notification.vcap.domain.NotificationServiceEnvironmentElement;
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

  private static final String NULL_PARAMETER_EXCEPTION_MESSAGE = "Required paramter is null";

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

            if ( requestReturnedError(notificationServiceResponse)  )
            {
              String details = notificationServiceResponse.getPayload() != null ? notificationServiceResponse.getPayload().toString() : null;
              throw new RequestException(notificationServiceResponse.getMessage(), details, notificationRequest.getRequestUrl(), notificationServiceResponse.getStatus().intValue(), notificationServiceResponse.getMessage());
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

  private boolean requestReturnedError(NotificationServiceResponse notificationServiceResponse)
  {
    Long status = notificationServiceResponse.getStatus();
    return  (!status.equals(NotificationServiceResponseStatus.Ok.getValue()) &&
             !status.equals(NotificationServiceResponseStatus.NotificationEmailMessageQueued.getValue()));
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
  public List<Configuration> getConfigurations(String token) throws RequestException, NotificationClientException{

    GetConfigurationsRequest getConfigurationsRequest = new GetConfigurationsRequest.GetConfigurationsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setToken(token)
        .build();

    return sendConfigurationRequest(getConfigurationsRequest);
  }

  @Override
  public Configuration createConfiguration(String token, CreateConfigurationRequestBody createConfigurationRequestBody) throws RequestException, NotificationClientException
  {
    Configuration configuration;
    CreateConfigurationRequest createConfigurationRequest = new CreateConfigurationRequest.CreateConfigurationRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setCreateConfigurationRequestBody(createConfigurationRequestBody)
        .setToken(token)
        .build();

    if (createConfigurationRequest.getCreateConfigurationRequestBody() != null) {
      _logger.debug(createConfigurationRequest.getCreateConfigurationRequestBody().toJson());
    }

    List<Configuration> configurations = sendConfigurationRequest(createConfigurationRequest);
    configuration = configurations != null ? configurations.get(0) : null;

    return configuration;
  }

  @Override
  public Configuration updateConfiguration(String token, Configuration configuration, UpdateConfigurationRequestBody updateConfigurationRequestBody)  throws RequestException, NotificationClientException {
    Configuration returnConfiguration = null;

    if (configuration == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Configuration parameter is null");
    }

    if (updateConfigurationRequestBody == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "UpdateConfigurationRequestBody parameter is null");
    }

    UpdateConfigurationRequest updateConfigurationRequest = new UpdateConfigurationRequest.UpdateConfigurationRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setConfigurationUuid(configuration.getUuid())
        .setUpdateConfigurationRequestBody(updateConfigurationRequestBody)
        .setToken(token)
        .build();


    List<Configuration> configurations = sendConfigurationRequest(updateConfigurationRequest);

    if (configurations != null)
    {
      returnConfiguration = configurations.get(0);
    }

    return returnConfiguration;
  }

  @Override
  public Configuration deleteConfiguration(String token, Configuration configuration) throws RequestException, NotificationClientException {

    Configuration returnConfiguration = null;

    if (configuration == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Configuration parameter is null");
    }

    DeleteConfigurationRequest deleteConfigurationRequest = new DeleteConfigurationRequest.DeleteConfigurationsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setConfigurationUuid(configuration.getUuid())
        .setToken(token)
        .build();

    List<Configuration> configurations  =  sendConfigurationRequest(deleteConfigurationRequest);

    if (configurations != null)
    {
      returnConfiguration = configurations.get(0);
    }

    return returnConfiguration;
  }

  @Override
  public SendEmailResponse sendTemplateEmail(String token, Configuration configuration,  Template template, SendTemplateEmailRequestBody sendTemplateEmailRequestBody) throws RequestException, NotificationClientException {
    SendEmailResponse sendEmailResponse = null;

    if (configuration == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Configuration parameter is null");
    }

    if (template == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Template parameter is null");
    }

    if (sendTemplateEmailRequestBody == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "SendTemplateEmailRequestBody parameter is null");
    }


    if (sendTemplateEmailRequestBody != null) {
      _logger.debug(sendTemplateEmailRequestBody.toJson());
    }

    SendEmailRequest sendEmailRequest = new SendEmailRequest.SendEmailRequestBuilder(this.baseUrl, this.version, this.tenantUuid, configuration.getUuid())
        .setSendEmailRequestBody(sendTemplateEmailRequestBody)
        .setTemplateUuid(template.getTemplateUuid())
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
  public SendEmailResponse sendEmail(String token, Configuration configuration, SendEmailRequestBody sendEmailRequestBody) throws RequestException, NotificationClientException {
    SendEmailResponse sendEmailResponse = null;
    if (sendEmailRequestBody != null) {
      _logger.debug(sendEmailRequestBody.toJson());
    }

    if (configuration == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Configuration parameter is null");
    }

    if (sendEmailRequestBody == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "SendEmailRequestBody parameter is null");
    }

    SendEmailRequest sendEmailRequest = new SendEmailRequest.SendEmailRequestBuilder(this.baseUrl, this.version, this.tenantUuid, configuration.getUuid())
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

  @Override
  public List<Template> getTemplates(String token) throws RequestException, NotificationClientException {

    GetTemplateRequest getTemplateRequest = new GetTemplateRequest.GetTemplateRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setToken(token)
        .build();

    return  sendTemplateRequest(getTemplateRequest);
  }

  @Override
  public Template createTemplate(String token, TemplateRequestBody templateRequestBody, InputStream templateFileInputStream) throws RequestException, NotificationClientException {
    Template template = null;

    CreateTemplateRequest createTemplateRequest = new CreateTemplateRequest.CreateTemplateRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateRequestBody(templateRequestBody)
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

    if (templates != null)
    {
      template = templates.get(0);
    }

    return template;
  }

  @Override
  public Template deleteTemplate(String token, Template template) throws RequestException, NotificationClientException {

    Template returnTemplate = null;

    if (template == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Template parameter is null");
    }

    DeleteTemplateRequest deleteTemplateRequest = new DeleteTemplateRequest.DeleteTemplateRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateUuid(template.getTemplateUuid())
        .setToken(token)
        .build();

    List<Template> templates = sendTemplateRequest(deleteTemplateRequest);
    if (templates != null) {
      returnTemplate = templates.get(0);
    }

    return returnTemplate;
  }

  @Override
  public List<Matcher> getMatchers(String token, Template template) throws RequestException, NotificationClientException {

    if (template == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Template parameter is null");
    }

    GetMatcherRequest getMatcherRequest = new GetMatcherRequest.GetMatcherRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateUuid(template.getTemplateUuid())
        .setToken(token)
        .build();

    return sendMatchersRequest(getMatcherRequest);

  }

  @Override
  public Matcher createMatcher(String token, Template template, MatchersRequestBody matchersRequestBody) throws RequestException, NotificationClientException {

    Matcher matcher = null;

    if (template == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Template parameter is null");
    }

    if (matchersRequestBody == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "MatchersRequestBody is null");
    }

    CreateMatchersRequest createMatchersRequest = new CreateMatchersRequest.CreateMatchersRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setMatchersRequestBody(matchersRequestBody)
        .setTemplateUuid(template.getTemplateUuid())
        .setToken(token)
        .build();

    List<Matcher> matchers =  sendMatchersRequest(createMatchersRequest);

    if (matchers != null)
    {
      matcher = matchers.get(0);
    }

    return matcher;
  }

  @Override
  public Matcher deleteMatcher(String token, Template template, Matcher matcher) throws RequestException, NotificationClientException {

    Matcher returnMatcher = null;

    if (template == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Template parameter is null");
    }

    if (matcher == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Matcher parameter is null");
    }
    
    DeleteMatcherRequest deleteMatcherRequest = new DeleteMatcherRequest.DeleteMatchersRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateUuid(template.getTemplateUuid())
        .setMatcherUuid(matcher.getMatchersUuid())
        .setToken(token)
        .build();

    List<Matcher> matchers =  sendMatchersRequest(deleteMatcherRequest);
    if (matchers != null)
    {
      returnMatcher = matchers.get(0);
    }

    return returnMatcher;
  }

  @Override
  public List<Recipient> getRecipients(String token, Template template, Matcher matcher) throws RequestException, NotificationClientException {

    if (template == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Template parameter is null");
    }

    if (matcher == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Matcher parameter is null");
    }

    GetRecipientsRequest getRecipientsRequest = new GetRecipientsRequest.GetRecipientsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setMatcherUuid(matcher.getMatchersUuid())
        .setTemplateUuid(template.getTemplateUuid())
        .setToken(token)
        .build();

    return sendRecipientsRequest(getRecipientsRequest);
  }

  @Override
  public List<Recipient> createRecipients(String token, Template template, Matcher matcher, CreateRecipientsRequestBody createRecipientsRequestBody) throws RequestException, NotificationClientException {

    if (template == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Template parameter is null");
    }

    if (matcher == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Matcher parameter is null");
    }

    if (createRecipientsRequestBody == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "CreateRecipientsRequestBody parameter is null");
    }

    CreateRecipientsRequest createRecipientsRequest = new CreateRecipientsRequest.CreateRecipientsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setCreateRecipientsRequestBody(createRecipientsRequestBody)
        .setTemplateUuid(template.getTemplateUuid())
        .setMatcherUuid(matcher.getMatchersUuid())
        .setToken(token)
        .build();

    return sendRecipientsRequest(createRecipientsRequest);
  }

  @Override
  public Recipient deleteRecipient(String token, Template template, Matcher matcher, Recipient recipient) throws RequestException, NotificationClientException {

    Recipient returnRecipient = null;

    if (template == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Template parameter is null");
    }

    if (matcher == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Matcher parameter is null");
    }

    if (recipient == null)
    {
      throw new NotificationClientException(NULL_PARAMETER_EXCEPTION_MESSAGE, "Recipient parameter is null");
    }

    DeleteRecipientsRequest deleteRecipientsRequest = new DeleteRecipientsRequest.DeleteRecipientsRequestBuilder(this.baseUrl, this.version, this.tenantUuid)
        .setTemplateUuid(template.getTemplateUuid())
        .setMatcherUuid(matcher.getMatchersUuid())
        .setRecipientUuid(recipient.getRecipientUuid())
        .setToken(token)
        .build();

    List<Recipient> recipients =  sendRecipientsRequest(deleteRecipientsRequest);

    if (recipients != null)
    {
      returnRecipient = recipients.get(0);
    }

    return returnRecipient;
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

    public NotificationServiceClientBuilder(NotificationServiceEnvironmentElement notificationServiceEnvironmentElement) {
      if (notificationServiceEnvironmentElement != null)
      {
        setTenantUuid(notificationServiceEnvironmentElement.getCredentials().getTenantUuid());
        setBaseUrl(notificationServiceEnvironmentElement.getCredentials().getCatalogUri());
        setVersion(notificationServiceEnvironmentElement.getCredentials().getVersion());
      }
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
