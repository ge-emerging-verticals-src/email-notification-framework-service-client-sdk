package com.ge.ev.notification;

import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.domain.NotificationEvent;
import com.ge.ev.notification.client.domain.Tenant;
import com.ge.ev.notification.client.exceptions.NotificationClientException;
import com.ge.ev.notification.client.exceptions.RequestException;
import com.ge.ev.notification.client.requests.configuration.ConfigurationRequestBody;
import com.ge.ev.notification.client.requests.email.SendEmailRequestBody;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
import com.ge.ev.notification.client.response.SendEmailResponse;
import java.io.IOException;
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
   * @param configurationUuid
   * @return
   */
  List<Configuration> getConfigurations(String token, String configurationUuid) throws  RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param configurationRequestBody
   * @return
   * @throws IOException
   * @throws RequestException
   */
  List<Configuration> createConfiguration(String token, ConfigurationRequestBody configurationRequestBody) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param configurationUuid
   * @param configurationRequestBody
   * @return
   * @throws IOException
   * @throws RequestException
   */
  List<Configuration> updateConfiguration(String token, String configurationUuid, ConfigurationRequestBody configurationRequestBody) throws RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param configurationUuid
   * @return
   * @throws IOException
   * @throws RequestException
   * @throws NotificationClientException
   */
  List<Configuration> deleteConfiguration(String token, String configurationUuid) throws  RequestException, NotificationClientException;

  /**
   *
   * @param token
   * @param configurationUuid
   * @return
   */
  SendEmailResponse sendEmail(String token, String configurationUuid, SendEmailRequestBody sendEmailRequestBody) throws RequestException, NotificationClientException;


  /**
   * 
   * @param token
   * @param notificationReferenceUuid
   * @return
   * @throws RequestException
   * @throws NotificationClientException
   */
  List<NotificationEvent> getEvents(String token, String notificationReferenceUuid)  throws RequestException, NotificationClientException;

}
