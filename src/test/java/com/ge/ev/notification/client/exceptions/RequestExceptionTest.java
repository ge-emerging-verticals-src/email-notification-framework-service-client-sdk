package com.ge.ev.notification.client.exceptions;


import static com.ge.ev.notification.Constants.BASEURL;
import static com.ge.ev.notification.Constants.STATUS;
import static com.ge.ev.notification.Constants.TEST_DETAILS;
import static com.ge.ev.notification.Constants.TEST_ERROR;
import static com.ge.ev.notification.Constants.TEST_MESSAGE;

import com.ge.ev.notification.client.exceptions.RequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RequestExceptionTest {



  @Test
  public void TestRequestExceptionBase() {
    RequestException requestException = new RequestException(TEST_MESSAGE,TEST_DETAILS, BASEURL, STATUS, TEST_ERROR);
    assert(requestException.getMessage().equals(TEST_MESSAGE));
    assert(requestException.getDetails().equals(TEST_DETAILS));
    assert(requestException.getStatus()==STATUS);
    assert(requestException.getStatusMessage().equals(TEST_ERROR));
    assert(requestException.getUrl().equals(BASEURL));
  }

}
