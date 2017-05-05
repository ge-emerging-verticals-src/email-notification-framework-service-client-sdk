package com.ge.ev.notification.client.requests.template;

/**
 * Created by 212391398 on 5/4/17.
 */
public class RecipientsRequest extends MatchersRequest {

  protected static final String ALL_RECIPIENTS_URI = "%s/recipients";
  protected static final String RECIPIENTS_URI = "%s/recipients/%s";

  protected String recipientUuid;

  protected RecipientsRequest(RecipientsRequestBuilder builder) {
    super(builder);
    this.recipientUuid = builder.getRecipientUuid();
  }

  @Override
  public String getRequestUri()
  {
    if (requestUri == null)
    {
      requestUri = (recipientUuid == null) ? String.format(ALL_RECIPIENTS_URI, super.getRequestUri()) : String.format(RECIPIENTS_URI, super.getRequestUri(), recipientUuid);
    }
    return requestUri;
  }

  public static class RecipientsRequestBuilder extends MatcherRequestBuilder
  {
    protected String recipientUuid;

    public RecipientsRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public String getRecipientUuid() {
      return recipientUuid;
    }
  }


}
