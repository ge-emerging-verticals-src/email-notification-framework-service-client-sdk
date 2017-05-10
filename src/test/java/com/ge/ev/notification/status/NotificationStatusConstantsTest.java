package com.ge.ev.notification.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by 212391398 on 5/8/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NotificationStatusConstantsTest
{

  @Test
  public
  void TestNotificationStatusResponse()
  {
    assert(NotificationStatusConstants.GENERAL_ERROR_VAL == -1);

    NotificationServiceResponseStatus notificationServiceResponseStatus = NotificationServiceResponseStatus.GeneralError;
    assert(notificationServiceResponseStatus.getValue() == NotificationStatusConstants.GENERAL_ERROR_VAL);
    
  }

}
