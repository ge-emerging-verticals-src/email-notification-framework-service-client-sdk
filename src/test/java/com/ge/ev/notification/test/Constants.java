package com.ge.ev.notification.test;

/**
 * Created by 212391398 on 5/3/17.
 */
public class Constants {
  public static final String BASEURL = "https://notification.predix.io";
  public static final String VERSION = "v1";
  public static final String TENANT_UUID = "2beb4e8d-7eba-4cc1-b584-3d95bf84aedc";
  public static final String TOKEN = "8536aed6-0422-4e53-bb7d-4fc967128c50";
  public static final String CONFIGURATION_UUID = "cd407863-e152-4b3f-9635-ce153311a12c";
  public static final String CONTENT_HEADER = "application/json";
  public static final String AUTHORIZATION_HEADER = "bearer " + TOKEN;
  public static final String TEST_UUID = "0959f9de-74a7-48b4-8f84-34ef67b1b5df";
  public static final String TEST_MESSAGE = "NotificationEmailMessageQueued";
  public static final String TEST_ERROR = "test error";
  public static final String SAMPLE_JSON = "{\"notificationStatus\":\"NotificationEmailMessageQueued\",\"errors\":[\"test error\"],\"notificationReferenceUuid\":\"0959f9de-74a7-48b4-8f84-34ef67b1b5df\"}";
  public static final String TEST_DETAILS = "test details";
  public static final Integer STATUS = 1000;
  public static final String TEST_VARIABLES = "test variables";
  public static final String NOTIFICATION_REFERENCE_UUID = "fae02293-e366-4e73-82ff-49699faae735";
  public static final String RECIPIENTS_JSON_ARRAY = "[{\"recipientName\":\"EV Notification Service\",\"email\":\"ev.notification@ge.com\",\"type\":\"to\"},{\"recipientName\":\"EV Notification Service\",\"email\":\"ev.notification@ge.com\",\"type\":\"to\"}]";
  public static final String EMAIL ="ev.notification@ge.com";
  public static final String NAME = "EV Notification Service";
  public static final String TYPE = "to";
  public static final String TEMPLATE_UUID = "2f9f0c1a-2ca5-4a86-a366-4b9e6db7253c";
  public static final String MATCHER_UUID = "3436eaef-ebd9-4b83-b673-4696f51684ea";
}
