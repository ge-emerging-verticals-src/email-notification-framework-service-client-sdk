package com.ge.ev.notification.client.requests.email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.util.JsonSerializableArrayList;

/**
 * Created by 212391398 on 5/2/17.
 */
@JsonInclude(Include.NON_EMPTY)
public class SendEmailRequestBody extends JsonObject {
  private String body;
  private JsonSerializableArrayList<SendEmailRequestBodyRecipient> recipients;
  private Boolean important;
  private String subject;
  private String fromEmail;
  private String fromName;

  protected SendEmailRequestBody(SendEmailRequestBodyBuilder builder) {
     this.body = builder.getBody();
     this.fromEmail = builder.getFromEmail();
     this.recipients = builder.getRecipients();
     this.important =  builder.getImportant();
     this.subject = builder.getSubject();
     this.fromName = builder.getFromName();
  }

  public String getBody ()
  {
    return body;
  }

  public void setBody (String body)
  {
    this.body = body;
  }

  public JsonSerializableArrayList<SendEmailRequestBodyRecipient>  getRecipients ()
  {
    return recipients;
  }

  public Boolean getImportant ()
  {
    return important;
  }

  public String getSubject ()
  {
    return subject;
  }

  public String getFromEmail ()
  {
    return fromEmail;
  }

  public String getFromName ()
  {
    return fromName;
  }

  public static class SendEmailRequestBodyBuilder
  {
    private String body;
    private JsonSerializableArrayList<SendEmailRequestBodyRecipient> recipients;
    private Boolean important;
    private String subject;
    private String fromEmail;
    private String fromName;

    public String getBody() {
      return body;
    }

    public SendEmailRequestBodyBuilder setBody(String body) {
      this.body = body;
      return this;
    }

    public JsonSerializableArrayList<SendEmailRequestBodyRecipient> getRecipients() {
      return recipients;
    }

    public SendEmailRequestBodyBuilder setRecipients(
        JsonSerializableArrayList<SendEmailRequestBodyRecipient> recipients) {
      this.recipients = recipients;
      return this;
    }

    public SendEmailRequestBodyBuilder addRecipients(SendEmailRequestBodyRecipient recipient) {
      if (recipients == null)
      {
        recipients = new JsonSerializableArrayList<>();
      }
      this.recipients.add(recipient);
      return this;
    }

    public Boolean getImportant() {
      return important;
    }

    public SendEmailRequestBodyBuilder setImportant(Boolean important) {
      this.important = important;
      return this;
    }

    public String getSubject() {
      return subject;
    }

    public SendEmailRequestBodyBuilder setSubject(String subject) {
      this.subject = subject;
      return this;
    }

    public String getFromEmail() {
      return fromEmail;
    }

    public SendEmailRequestBodyBuilder setFromEmail(String fromEmail) {
      this.fromEmail = fromEmail;
      return this;
    }

    public String getFromName() {
      return fromName;
    }

    public SendEmailRequestBodyBuilder setFromName(String fromName) {
      this.fromName = fromName;
      return this;
    }

    public SendEmailRequestBody build()
    {
      return new SendEmailRequestBody(this);
    }
  }

}
