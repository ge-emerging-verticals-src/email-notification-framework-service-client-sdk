package com.ge.ev.notification.vcap.exceptions;

import static com.ge.ev.notification.Constants.TEST_MESSAGE;
import static com.ge.ev.notification.Constants.TEST_VARIABLES;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ServiceEnvironmentExceptionTest {

  private static final String VCAP = "VCAP";

  @Test
  public void TestServiceEnvironmentException() {
    ServiceEnvironmentException serviceEnvironmentException = new ServiceEnvironmentException(TEST_MESSAGE, TEST_VARIABLES);
    assert (serviceEnvironmentException.getMessage().equals(TEST_MESSAGE));
    assert (serviceEnvironmentException.getEnvironmentVariables().equals(TEST_VARIABLES));

    serviceEnvironmentException.setEnvironmentVariables(VCAP);
    assert (serviceEnvironmentException.getEnvironmentVariables().equals(VCAP));
  }
}
