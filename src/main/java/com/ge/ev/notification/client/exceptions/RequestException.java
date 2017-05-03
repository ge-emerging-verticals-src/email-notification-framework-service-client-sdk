package com.ge.ev.notification.client.exceptions;

/**
 * Created by 212391398 on 5/1/17.
 */
public class RequestException extends Exception {

  private String url;
  private int status;
  private String statusMessage;

  public RequestException(String message, String url, int status, String statusMessage)
  {
    super(message);
    this.url = url;
    this.status = status;
    this.statusMessage = statusMessage;
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

}
