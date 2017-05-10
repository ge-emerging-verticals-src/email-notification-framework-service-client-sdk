package com.ge.ev.notification.client.requests.template;

import com.ge.ev.notification.client.requests.NotificationRequestImpl;

/**
 * Created by 212391398 on 5/3/17.
 */
public class TemplateRequest extends NotificationRequestImpl {
  protected static final String ALL_TEMPLATES_URI = "/tenants/%s/templates";
  protected static final String TEMPLATE_URI = "/tenants/%s/templates/%s";

  protected String templateUuid;

  protected TemplateRequest(TemplateRequestBuilder builder) {
    super(builder);
    this.templateUuid = builder.getTemplateUuid();
  }

  @Override
  public String getRequestUri()
  {
    if (requestUri == null)
    {
      requestUri = templateUuid != null ? String
          .format(TEMPLATE_URI, tenantUuid, templateUuid)
          : String.format(ALL_TEMPLATES_URI, tenantUuid);
    }
    return requestUri;
  }

  public String getTemplateUuid() {
    return templateUuid;
  }

  protected static class TemplateRequestBuilder extends NotificationRequestBuilder
  {
    protected String templateUuid;


    public TemplateRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public String getTemplateUuid() {
      return templateUuid;
    }
  }

}
