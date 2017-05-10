package com.ge.ev.notification.client.requests.template;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateRequestBody extends JsonObject implements NotificationRequestBody {

  private String subjectTemplate;
  private String description;
  private String name;
  private String domain;

  private TemplateRequestBody(TemplateRequestBodyBuilder builder)
  {
    subjectTemplate = builder.getSubjectTemplate();
    description = builder.getDescription();
    name = builder.getName();
    domain = builder.getDomain();
  }

  public String getSubjectTemplate() {
    return subjectTemplate;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public String getDomain() {
    return domain;
  }

  public static class TemplateRequestBodyBuilder
  {
    private String subjectTemplate;
    private String description;
    private String name;
    private String domain;

    public TemplateRequestBodyBuilder(String domain, String name, String description)
    {
      this.domain = domain;
      this.name = name;
      this.description = description;
    }

    public String getSubjectTemplate() {
      return subjectTemplate;
    }

    public TemplateRequestBodyBuilder setSubjectTemplate(String subjectTemplate) {
      this.subjectTemplate = subjectTemplate;
      return this;
    }

    public String getDescription() {
      return description;
    }

    public TemplateRequestBodyBuilder setDescription(String description) {
      this.description = description;
      return this;
    }

    public String getName() {
      return name;
    }

    public TemplateRequestBodyBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public String getDomain() {
      return domain;
    }

    public TemplateRequestBodyBuilder setDomain(String domain) {
      this.domain = domain;
      return this;
    }

    public TemplateRequestBody build()
    {
      return new TemplateRequestBody(this);
    }

  }

}