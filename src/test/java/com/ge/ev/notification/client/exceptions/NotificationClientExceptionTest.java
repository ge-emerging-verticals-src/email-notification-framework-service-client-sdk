package com.ge.ev.notification.client.exceptions;

import static com.ge.ev.notification.Constants.TEST_DETAILS;
import static com.ge.ev.notification.Constants.TEST_MESSAGE;

import com.ge.ev.notification.client.exceptions.NotificationClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NotificationClientExceptionTest {

  @Test
  public void TestNotificationClientException() {
    NotificationClientException notificationClientException = new NotificationClientException(TEST_MESSAGE, TEST_DETAILS);
    assert(notificationClientException.getMessage().equals(TEST_MESSAGE));
    assert(notificationClientException.getDetails().equals(TEST_DETAILS));
  }

}
