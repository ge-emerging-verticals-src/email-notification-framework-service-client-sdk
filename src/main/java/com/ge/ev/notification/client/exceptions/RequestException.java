package com.ge.ev.notification.client.exceptions;

/**
 * Created by 212391398 on 5/1/17.
 */
public class RequestException extends Exception {

  private String url;
  private int status;
  private String statusMessage;
  private String details;

  public RequestException(String message, String details,  String url, int status, String statusMessage)
  {
    super(message);
    this.url = url;
    this.status = status;
    this.statusMessage = statusMessage;
    this.details = details;
  }

  public String getUrl() {
    return url;
  }

  public int getStatus() {
    return status;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public String getDetails() {
    return details;
  }
}
