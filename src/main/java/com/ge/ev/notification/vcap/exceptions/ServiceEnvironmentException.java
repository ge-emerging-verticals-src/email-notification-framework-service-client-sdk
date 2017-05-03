package com.ge.ev.notification.vcap.exceptions;

/**
 * Created by 212391398 on 4/17/17.
 */
public class ServiceEnvironmentException extends Exception {
  private String environmentVariables;
  public ServiceEnvironmentException(String message, String environmentVariables)
  {
    super(message);
    this.environmentVariables = environmentVariables;
  }

  public String getEnvironmentVariables() {
    return environmentVariables;
  }

  public void setEnvironmentVariables(String environmentVariables) {
    this.environmentVariables = environmentVariables;
  }
}
