package com.ge.ev.notification.client.requests.template;

/**
 * Created by 212391398 on 5/4/17.
 */
public class MatchersRequest extends TemplateRequest {

  protected static final String ALL_MATCHERS_URI = "%s/matchers";
  protected static final String MATCHERS_URI = "%s/matchers/%s";

  protected String matcherUuid;

  protected MatchersRequest(MatcherRequestBuilder builder) {
    super(builder);
    this.matcherUuid = builder.getMatcherUuid();
  }

  @Override
  public String getRequestUri()
  {
    if (requestUri == null)
    {
      requestUri = (matcherUuid == null) ? String.format(ALL_MATCHERS_URI, super.getRequestUri()) : String.format(MATCHERS_URI, super.getRequestUri(), matcherUuid);
    }
    return requestUri;
  }

  public static class MatcherRequestBuilder extends TemplateRequestBuilder
  {
    protected String matcherUuid;

    public MatcherRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public String getMatcherUuid() {
      return matcherUuid;
    }
  }

}
