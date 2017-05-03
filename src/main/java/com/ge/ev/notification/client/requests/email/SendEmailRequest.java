package com.ge.ev.notification.client.requests.email;

import com.ge.ev.notification.client.requests.NotificationRequestImpl;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/2/17.
 */
public class SendEmailRequest extends NotificationRequestImpl {

  protected static final String URL = "%s/%s%s";
  protected static final String EMAIL_URI = "/tenants/%s/email?configuration=%s";
  protected static final String TEMPLATE_EMAIL_URI = "/tenants/%s/email/template/%s?configuration=%s";

  private String configurationUuid;
  private String templateUuid;
  private SendEmailRequestBody sendEmailRequestBody;

  protected SendEmailRequest(SendEmailRequestBuilder builder)
  {
    super(builder);
    this.configurationUuid = builder.getConfigurationUuid();
    this.templateUuid = builder.getTemplateUuid();
    this.sendEmailRequestBody = builder.getSendEmailRequestBody();

    HttpPost post = new HttpPost(getRequestUrl());
    if (sendEmailRequestBody != null) {
      try {
        post.setEntity(new StringEntity(sendEmailRequestBody.toJson()));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    super.requestBase = post;
  }

  public String getConfigurationUuid() {
    return configurationUuid;
  }

  public String getTemplateUuid() {
    return templateUuid;
  }

  public SendEmailRequestBody getSendEmailRequestBody() {
    return sendEmailRequestBody;
  }

  @Override
  public String getRequestUri()
  {
    if (requestUri == null)
    {
      requestUri = templateUuid != null ? String
          .format(TEMPLATE_EMAIL_URI, tenantUuid, templateUuid, configurationUuid)
          : String.format(EMAIL_URI, tenantUuid, configurationUuid);
    }
    return requestUri;
  }

  public static class SendEmailRequestBuilder extends NotificationRequestBuilder
  {
    private String configurationUuid;
    private String templateUuid;
    private SendEmailRequestBody sendEmailRequestBody;

    public SendEmailRequestBuilder( String baseUrl, String version, String tenantUuid, String configurationUuid )
    {
      super(baseUrl, version, tenantUuid);
      this.configurationUuid = configurationUuid;
    }

    public String getConfigurationUuid() { return configurationUuid; }

    public String getTemplateUuid() {
      return templateUuid;
    }

    public SendEmailRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public SendEmailRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public SendEmailRequestBody getSendEmailRequestBody() {
      return sendEmailRequestBody;
    }

    public SendEmailRequestBuilder setSendEmailRequestBody(
        SendEmailRequestBody sendEmailRequestBody) {
      this.sendEmailRequestBody = sendEmailRequestBody;
      return this;
    }

    public SendEmailRequest build()
    {
      return new SendEmailRequest(this);
    }
  }


}
