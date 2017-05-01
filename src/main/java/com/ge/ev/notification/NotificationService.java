package com.ge.ev.notification;

import com.ge.ev.notification.client.domain.Configuration;
import com.ge.ev.notification.client.domain.Tenant;
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
  Tenant getTenant(String token) throws IOException;

  /**
   *
   * @param token
   * @param updateTenantConfigurationRequestBody
   * @return
   */
  Tenant updateTenant(String token, UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody) throws IOException;

  /**
   *
   * @param token
   * @param configurationUuid
   * @return
   */
  List<Configuration> getConfigurations(String token, String configurationUuid) throws IOException;

  List<Configuration> createConfiguration(String token);

  List<Configuration> updateConfiguration(String token);

  Configuration deleteConfiguration(String token);

}
