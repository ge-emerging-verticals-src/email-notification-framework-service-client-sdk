package com.ge.ev.notification.client.requests.event;

import com.ge.ev.notification.client.requests.NotificationRequestImpl;
import org.apache.http.client.methods.HttpGet;


public class GetEventsRequest extends NotificationRequestImpl {

  protected static final String ALL_EVENTS_URI = "/tenants/%s/events";
  protected static final String EVENT_URI = "/tenants/%s/events?tag=%s";

  private String notificationReferenceUuid;

  protected GetEventsRequest(GetEventsRequestBuilder notificationRequestBuilder) {
    super(notificationRequestBuilder);
    this.notificationReferenceUuid = notificationRequestBuilder.getNotificationReferenceUuid();

    super.requestBase = new HttpGet(getRequestUrl());
  }

  @Override
  public String getRequestUri()
  {
    if (requestUri == null)
    {
      requestUri = notificationReferenceUuid != null ? String
          .format(EVENT_URI, tenantUuid, notificationReferenceUuid)
          : String.format(ALL_EVENTS_URI, tenantUuid);
    }
    return requestUri;
  }

  public String getNotificationReferenceUuid() {
    return notificationReferenceUuid;
  }

  public static class GetEventsRequestBuilder extends NotificationRequestBuilder
  {
    private String notificationReferenceUuid;

    public GetEventsRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public String getNotificationReferenceUuid() {
      return notificationReferenceUuid;
    }

    public GetEventsRequestBuilder setNotificationReferenceUuid(String notificationReferenceUuid) {
      this.notificationReferenceUuid = notificationReferenceUuid;
      return this;
    }

    public GetEventsRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public GetEventsRequest build()
    {
      return new GetEventsRequest(this);
    }
  }

}
