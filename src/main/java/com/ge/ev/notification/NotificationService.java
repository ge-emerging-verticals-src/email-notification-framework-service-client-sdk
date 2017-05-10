package com.ge.ev.notification;

import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.domain.Matcher;
import com.ge.ev.notification.client.domain.NotificationEvent;
import com.ge.ev.notification.client.domain.Recipient;
import com.ge.ev.notification.client.domain.Template;
import com.ge.ev.notification.client.domain.Tenant;
import com.ge.ev.notification.client.exceptions.NotificationClientException;
import com.ge.ev.notification.client.exceptions.RequestException;
import com.ge.ev.notification.client.requests.configuration.CreateConfigurationRequestBody;
import com.ge.ev.notification.client.requests.configuration.UpdateConfigurationRequestBody;
import com.ge.ev.notification.client.requests.email.SendEmailRequestBody;
import com.ge.ev.notification.client.requests.email.SendTemplateEmailRequestBody;
import com.ge.ev.notification.client.requests.template.MatchersRequestBody;
import com.ge.ev.notification.client.requests.template.CreateRecipientsRequestBody;
import com.ge.ev.notification.client.requests.template.TemplateRequestBody;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
import com.ge.ev.notification.client.response.SendEmailResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 212391398 on 5/1/17.
 */
public interface NotificationService {

  /**
   *
   * @param token
   * @return
   */
  Tenant getTenant(String token) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param updateTenantConfigurationRequestBody
   * @return
   */
  Tenant updateTenant(String token, UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @return
   */
  List<Configuration> getConfigurations(String token) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param createConfigurationRequestBody
   * @return
   * @throws IOException
   * @throws RequestException
   */
  Configuration createConfiguration(String token, CreateConfigurationRequestBody createConfigurationRequestBody) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param configuration
   * @param updateConfigurationRequestBody
   * @return
   * @throws IOException
   * @throws RequestException
   */
  Configuration updateConfiguration(String token, Configuration configuration, UpdateConfigurationRequestBody updateConfigurationRequestBody) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param configuration
   * @return
   * @throws IOException
   * @throws RequestException
   * @throws NotificationClientException
   */
  Configuration deleteConfiguration(String token, Configuration configuration) throws  RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param configuration
   * @return
   */
  SendEmailResponse sendEmail(String token, Configuration configuration, SendEmailRequestBody sendEmailRequestBody) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param configuration
   * @return
   */
  SendEmailResponse sendTemplateEmail(String token, Configuration configuration,  Template template, SendTemplateEmailRequestBody sendTemplateEmailRequestBody) throws RequestException, NotificationClientException;

  /**
   * 
   * @param token
   * @param notificationReferenceUuid
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
  List<NotificationEvent> getEvents(String token, String notificationReferenceUuid) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
   List<Template> getTemplates(String token) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param templateRequestBody
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
   Template createTemplate(String token, TemplateRequestBody templateRequestBody, InputStream templateFileInputStream) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param template
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
   Template deleteTemplate(String token, Template template) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param template
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
   List<Matcher> getMatchers(String token, Template template)  throws RequestException, NotificationClientException;


  /**
   *
   * @param token
   * @param template
   * @param matchersRequestBody
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
   Matcher createMatcher(String token, Template template, MatchersRequestBody matchersRequestBody) throws RequestException, NotificationClientException;


  /**
   *
   * @param token
   * @param template
   * @param matcher
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
  Matcher deleteMatcher(String token, Template template, Matcher matcher) throws RequestException, NotificationClientException;


  /**
   *
   * @param token
   * @param template
   * @param matcher
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
  List<Recipient> getRecipients(String token, Template template, Matcher matcher )  throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param template
   * @param matcher
   * @param createRecipientsRequestBody
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
  List<Recipient> createRecipients(String token, Template template, Matcher matcher, CreateRecipientsRequestBody createRecipientsRequestBody ) throws RequestException, NotificationClientException;


  /**
   *
   * @param token
   * @param template
   * @param matcher
   * @param recipient
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
  Recipient deleteRecipient(String token,  Template template, Matcher matcher,  Recipient recipient ) throws RequestException, NotificationClientException;

}
