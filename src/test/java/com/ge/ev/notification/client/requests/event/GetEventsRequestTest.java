package com.ge.ev.notification.client.requests.event;

import static com.ge.ev.notification.Constants.BASEURL;
import static com.ge.ev.notification.Constants.NOTIFICATION_REFERENCE_UUID;
import static com.ge.ev.notification.Constants.TENANT_UUID;
import static com.ge.ev.notification.Constants.TOKEN;
import static com.ge.ev.notification.Constants.VERSION;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetEventsRequestTest {

  private static final String ALL_EVENT_REQUEST_URL =BASEURL + "/" + VERSION + "/tenants/" + TENANT_UUID + "/events";
  private static final String EVENT_REQUEST_URL = ALL_EVENT_REQUEST_URL + "?tag=" + NOTIFICATION_REFERENCE_UUID;

  @Test
  public void TestGetEventsRequest()
  {
    GetEventsRequest getEventsRequest = new GetEventsRequest.GetEventsRequestBuilder(BASEURL,VERSION,TENANT_UUID)
        .setToken(TOKEN)
        .build();

    assert(getEventsRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getEventsRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/events"));
    assert(getEventsRequest.getRequest().getMethod().equals("GET"));
    assert(getEventsRequest.getRequest().getURI().toString().equals(ALL_EVENT_REQUEST_URL));
    assert(getEventsRequest.getRequestUrl().equals(ALL_EVENT_REQUEST_URL));
    assert(getEventsRequest.getToken().equals(TOKEN));

     getEventsRequest = new GetEventsRequest.GetEventsRequestBuilder(BASEURL,VERSION,TENANT_UUID)
        .setNotificationReferenceUuid(NOTIFICATION_REFERENCE_UUID)
        .setToken(TOKEN)
        .build();

    assert(getEventsRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getEventsRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/events" + "?tag=" + NOTIFICATION_REFERENCE_UUID));
    assert(getEventsRequest.getRequest().getMethod().equals("GET"));
    assert(getEventsRequest.getRequest().getURI().toString().equals(EVENT_REQUEST_URL));
    assert(getEventsRequest.getRequestUrl().equals(EVENT_REQUEST_URL));
    assert(getEventsRequest.getNotificationReferenceUuid().equals(NOTIFICATION_REFERENCE_UUID));
    assert(getEventsRequest.getToken().equals(TOKEN));
  }

}
