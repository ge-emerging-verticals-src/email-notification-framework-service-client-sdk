package com.ge.ev.notification;

import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.domain.Tenant;
import com.ge.ev.notification.client.exceptions.NotificationClientException;
import com.ge.ev.notification.client.exceptions.RequestException;
import com.ge.ev.notification.client.requests.configuration.ConfigurationRequestBody;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
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
  Tenant getTenant(String token) throws IOException, RequestException;

  /**
   *
   * @param token
   * @param updateTenantConfigurationRequestBody
   * @return
   */
  Tenant updateTenant(String token, UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody)
      throws IOException, RequestException;

  /**
   *
   * @param token
   * @param configurationUuid
   * @return
   */
  List<Configuration> getConfigurations(String token, String configurationUuid) throws IOException, RequestException;

  /**
   *
   * @param token
   * @param configurationRequestBody
   * @return
   * @throws IOException
   * @throws RequestException
   */
  List<Configuration> createConfiguration(String token, ConfigurationRequestBody configurationRequestBody) throws IOException, RequestException;

  /**
   *
   * @param token
   * @param configurationUuid
   * @param configurationRequestBody
   * @return
   * @throws IOException
   * @throws RequestException
   */
  List<Configuration> updateConfiguration(String token, String configurationUuid, ConfigurationRequestBody configurationRequestBody) throws IOException, RequestException;

  /**
   *
   * @param token
   * @param configurationUuid
   * @return
   * @throws IOException
   * @throws RequestException
   * @throws NotificationClientException
   */
  List<Configuration> deleteConfiguration(String token, String configurationUuid) throws IOException, RequestException, NotificationClientException;

}
