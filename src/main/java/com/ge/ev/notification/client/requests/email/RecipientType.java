package com.ge.ev.notification.client.requests.email;

/**
 * Created by 212391398 on 5/5/17.
 */
public enum RecipientType {
  to("to"),
  cc("cc"),
  bcc("bcc");

  private String value;

  RecipientType(String value)
  {
    this.value = value;
  }
}
