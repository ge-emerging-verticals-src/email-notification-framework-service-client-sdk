package com.ge.ev.notification.client.exceptions;

/**
 * Created by 212391398 on 5/2/17.
 */
public class NotificationClientException extends Exception {

  private String details;
  public NotificationClientException(String message, String details)
  {
    super(message);
    this.details = details;
  }

  public String getDetails() {
    return details;
  }

}
