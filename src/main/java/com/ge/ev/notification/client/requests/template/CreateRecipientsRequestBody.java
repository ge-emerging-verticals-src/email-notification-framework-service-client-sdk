package com.ge.ev.notification.client.requests.template;

import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 212391398 on 5/4/17.
 */
public class CreateRecipientsRequestBody extends JsonObject implements NotificationRequestBody {

  private List<String> recipients;

  public CreateRecipientsRequestBody(CreateRecipientsRequestBodyBuilder builder) {
    recipients = builder.getRecipients();
  }

  public List<String> getRecipients() {
    return recipients;
  }

  public static class CreateRecipientsRequestBodyBuilder
  {
    private ArrayList<String> recipients;

    public CreateRecipientsRequestBodyBuilder()
    {
      recipients = new ArrayList<>();
    }

    public List<String> getRecipients() {
      return recipients;
    }

    public CreateRecipientsRequestBodyBuilder addRecipient(String recipient) {
      recipients.add(recipient);
      return this;
    }
    public CreateRecipientsRequestBody build()
    {
      return new CreateRecipientsRequestBody(this);
    }
  }

}
