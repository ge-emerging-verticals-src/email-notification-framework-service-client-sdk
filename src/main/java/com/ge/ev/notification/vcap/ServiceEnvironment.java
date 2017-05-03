package com.ge.ev.notification.vcap;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.ev.notification.vcap.domain.NotificationServiceEnvironmentElement;
import com.ge.ev.notification.vcap.exceptions.ServiceEnvironmentException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 212391398 on 4/17/17.
 */
public class ServiceEnvironment {

  private final static String VCAP_SERVICES = "VCAP_SERVICES";
  private final static String NOTIFICATION_SEVICE_NAME = "notification";

  private static ObjectMapper mapper = new ObjectMapper();

  List<NotificationServiceEnvironmentElement> notificationServiceEnvironmentElements = new ArrayList<>();

  public ServiceEnvironment() throws ServiceEnvironmentException
  {
    String vcapServiceString = System.getenv(VCAP_SERVICES);
    createEnvironment(vcapServiceString);
  }

  public ServiceEnvironment(String vcapServiceString) throws ServiceEnvironmentException
  {
      createEnvironment(vcapServiceString);
  }

  public NotificationServiceEnvironmentElement getNotificationServiceElementByName(String name)
  {
    for (NotificationServiceEnvironmentElement notificationServiceEnvironmentElement : notificationServiceEnvironmentElements)
    {
      if (notificationServiceEnvironmentElement.getName().equals(name))
      {
        return notificationServiceEnvironmentElement;
      }
    }
    return null;
  }

  private void createEnvironment(String vcap) throws ServiceEnvironmentException
  {
    if (vcap == null)
    {
      throw new ServiceEnvironmentException("VCAP Service Environment not found", null);
    }

    LinkedHashMap<String, Object> vcapServicesMap = new LinkedHashMap<>();
    try
    {
      vcapServicesMap = mapper.readValue(vcap , vcapServicesMap.getClass());
    }
    catch (IOException e)
    {
      throw new ServiceEnvironmentException("Could not parse VCAP Environment", vcap);
    }

    List<LinkedHashMap<String, Object>> maps = (List<LinkedHashMap<String, Object>>) vcapServicesMap.get(NOTIFICATION_SEVICE_NAME);

    if (maps != null)
    {
        for (LinkedHashMap<String, Object> m : maps) {
          NotificationServiceEnvironmentElement notificationServiceEnvironmentElement;
          try {
            notificationServiceEnvironmentElement = mapper.readValue(mapper.writeValueAsString(m), NotificationServiceEnvironmentElement.class);
          } catch (IOException e) {
            throw new ServiceEnvironmentException("Could not parse VCAP Environment", m.toString());
          }
          if (notificationServiceEnvironmentElement != null) {
            notificationServiceEnvironmentElements.add(notificationServiceEnvironmentElement);
          }
        }
    }


  }

}
