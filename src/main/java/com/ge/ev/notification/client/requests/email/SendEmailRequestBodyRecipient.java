package com.ge.ev.notification.client.requests.email;

import com.ge.ev.notification.client.json.JsonObject;

/**
 * Created by 212391398 on 5/2/17.
 */
public class SendEmailRequestBodyRecipient extends JsonObject {
  private String recipientName;

  private String email;

  private String type;

  public SendEmailRequestBodyRecipient(SendEmailRequestBodyRecipientBuilder builder) {
    this.recipientName = builder.getRecipientName();
    this.email = builder.getEmail();
    this.type = builder.getType();
  }

  public String getRecipientName ()
  {
    return recipientName;
  }

  public String getEmail ()
  {
    return email;
  }

  public String getType ()
  {
    return type;
  }

  public static class SendEmailRequestBodyRecipientBuilder
  {
    private String recipientName;

    private String email;

    private String type;

    public SendEmailRequestBodyRecipientBuilder(String recipientName, String email, String type) {
      this.recipientName = recipientName;
      this.email = email;
      this.type = type;
    }

    public String getRecipientName() {
      return recipientName;
    }

    public SendEmailRequestBodyRecipientBuilder setRecipientName(String recipientName) {
      this.recipientName = recipientName;
      return this;
    }

    public String getEmail() {
      return email;
    }

    public SendEmailRequestBodyRecipientBuilder setEmail(String email) {
      this.email = email;
      return this;
    }

    public String getType() {
      return type;
    }

    public SendEmailRequestBodyRecipientBuilder setType(String type) {
      this.type = type;
      return this;
    }

    public SendEmailRequestBodyRecipient build()
    {
      return new SendEmailRequestBodyRecipient(this);
    }
  }

}
