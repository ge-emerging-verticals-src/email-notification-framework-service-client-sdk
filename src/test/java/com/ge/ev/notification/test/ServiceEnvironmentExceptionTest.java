package com.ge.ev.notification.test;

import static com.ge.ev.notification.test.Constants.TEST_MESSAGE;
import static com.ge.ev.notification.test.Constants.TEST_VARIABLES;

import com.ge.ev.notification.vcap.exceptions.ServiceEnvironmentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ServiceEnvironmentExceptionTest {


  @Test
  public void TestServiceEnvironmentException() {
    ServiceEnvironmentException serviceEnvironmentException = new ServiceEnvironmentException(TEST_MESSAGE, TEST_VARIABLES);
    assert (serviceEnvironmentException.getMessage().equals(TEST_MESSAGE));
    assert(serviceEnvironmentException.getEnvironmentVariables().equals(TEST_VARIABLES));
  }

}
