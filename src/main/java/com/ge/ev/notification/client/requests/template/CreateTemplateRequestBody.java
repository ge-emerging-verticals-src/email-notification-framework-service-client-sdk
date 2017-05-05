package com.ge.ev.notification.client.requests.template;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTemplateRequestBody extends JsonObject implements NotificationRequestBody {

  private String subjectTemplate;
  private String description;
  private String name;
  private String domain;

  private  CreateTemplateRequestBody(CreateTemplateRequestBodyBuilder createTemplateRequestBodyBuilder)
  {
    subjectTemplate = createTemplateRequestBodyBuilder.getSubjectTemplate();
    description = createTemplateRequestBodyBuilder.getDescription();
    name = createTemplateRequestBodyBuilder.getName();
    domain = createTemplateRequestBodyBuilder.getDomain();
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

  public static class CreateTemplateRequestBodyBuilder
  {
    private String subjectTemplate;
    private String description;
    private String name;
    private String domain;

    public CreateTemplateRequestBodyBuilder(String domain, String name, String description)
    {
      this.domain = domain;
      this.name = name;
      this.description = description;
    }

    public String getSubjectTemplate() {
      return subjectTemplate;
    }

    public CreateTemplateRequestBodyBuilder setSubjectTemplate(String subjectTemplate) {
      this.subjectTemplate = subjectTemplate;
      return this;
    }

    public String getDescription() {
      return description;
    }

    public CreateTemplateRequestBodyBuilder setDescription(String description) {
      this.description = description;
      return this;
    }

    public String getName() {
      return name;
    }

    public CreateTemplateRequestBodyBuilder setName(String name) {
      this.name = name;
      return this;
    }

    public String getDomain() {
      return domain;
    }

    public CreateTemplateRequestBodyBuilder setDomain(String domain) {
      this.domain = domain;
      return this;
    }

    public CreateTemplateRequestBody build()
    {
      return new CreateTemplateRequestBody(this);
    }

  }

}