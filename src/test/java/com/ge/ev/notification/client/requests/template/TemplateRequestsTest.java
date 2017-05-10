package com.ge.ev.notification.client.requests.template;

import static com.ge.ev.notification.Constants.ALL_TEMPLATE_REQUEST_URL;
import static com.ge.ev.notification.Constants.BASEURL;
import static com.ge.ev.notification.Constants.TEMPLATE_DESCRIPTION;
import static com.ge.ev.notification.Constants.TEMPLATE_DOMAIN;
import static com.ge.ev.notification.Constants.TEMPLATE_NAME;
import static com.ge.ev.notification.Constants.TEMPLATE_REQUEST_URL;
import static com.ge.ev.notification.Constants.TEMPLATE_SUBJECT;
import static com.ge.ev.notification.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.Constants.TENANT_UUID;
import static com.ge.ev.notification.Constants.TOKEN;
import static com.ge.ev.notification.Constants.VERSION;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TemplateRequestsTest {

  @Test
  public void TestTemplateRequests() {
    GetTemplateRequest getTemplateRequest = new GetTemplateRequest.GetTemplateRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(getTemplateRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getTemplateRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(getTemplateRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/templates/" + TEMPLATE_UUID));
    assert(getTemplateRequest.getRequest().getMethod().equals("GET"));
    assert(getTemplateRequest.getRequest().getURI().toString().equals(TEMPLATE_REQUEST_URL));
    assert(getTemplateRequest.getRequestUrl().equals(TEMPLATE_REQUEST_URL));
    assert(getTemplateRequest.getToken().equals(TOKEN));

    getTemplateRequest = new GetTemplateRequest.GetTemplateRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setToken(TOKEN)
        .build();

    assert(getTemplateRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getTemplateRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/templates"));
    assert(getTemplateRequest.getRequest().getMethod().equals("GET"));
    assert(getTemplateRequest.getRequest().getURI().toString().equals(ALL_TEMPLATE_REQUEST_URL));
    assert(getTemplateRequest.getRequestUrl().equals(ALL_TEMPLATE_REQUEST_URL));
    assert(getTemplateRequest.getToken().equals(TOKEN));
  }

  @Test
  public void TestTemplateCreateRequests() {
    TemplateRequestBody templateRequestBody = new TemplateRequestBody.TemplateRequestBodyBuilder(TEMPLATE_DOMAIN, TEMPLATE_NAME, TEMPLATE_DESCRIPTION)
        .setSubjectTemplate(TEMPLATE_SUBJECT)
        .build();

    assert(templateRequestBody.getSubjectTemplate().equals(TEMPLATE_SUBJECT));
    assert(templateRequestBody.getDescription().equals(TEMPLATE_DESCRIPTION));
    assert(templateRequestBody.getDomain().equals(TEMPLATE_DOMAIN));
    assert(templateRequestBody.getName().equals(TEMPLATE_NAME));

     templateRequestBody = new TemplateRequestBody.TemplateRequestBodyBuilder(TEMPLATE_DOMAIN, TEMPLATE_NAME, TEMPLATE_DESCRIPTION)
        .setSubjectTemplate(TEMPLATE_SUBJECT)
         .setDomain("domain")
         .setName("name")
         .setDescription("description")
        .build();

    assert(templateRequestBody.getSubjectTemplate().equals(TEMPLATE_SUBJECT));
    assert(templateRequestBody.getDescription().equals("description"));
    assert(templateRequestBody.getDomain().equals("domain"));
    assert(templateRequestBody.getName().equals("name"));

    CreateTemplateRequest createTemplateRequest = new CreateTemplateRequest.CreateTemplateRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setTemplateRequestBody(templateRequestBody)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(createTemplateRequest.getTenantUuid().equals(TENANT_UUID));
    assert(createTemplateRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(createTemplateRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/templates/" + TEMPLATE_UUID));
    assert(createTemplateRequest.getRequest().getMethod().equals("POST"));
    assert(createTemplateRequest.getRequest().getURI().toString().equals(TEMPLATE_REQUEST_URL));
    assert(createTemplateRequest.getRequestUrl().equals(TEMPLATE_REQUEST_URL));
    assert(createTemplateRequest.getToken().equals(TOKEN));
  }

  @Test
  public void TestTemplateUpdateReqests() {

    TemplateRequestBody templateRequestBody = new TemplateRequestBody.TemplateRequestBodyBuilder(TEMPLATE_DOMAIN, TEMPLATE_NAME, TEMPLATE_DESCRIPTION)
        .setSubjectTemplate(TEMPLATE_SUBJECT)
        .build();

    UpdateTemplateRequest updateTemplateRequest =  new UpdateTemplateRequest.UpdateTemplateRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setTemplateRequestBody(templateRequestBody)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(updateTemplateRequest.getTenantUuid().equals(TENANT_UUID));
    assert(updateTemplateRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(updateTemplateRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/templates/" + TEMPLATE_UUID));
    assert(updateTemplateRequest.getRequest().getMethod().equals("PATCH"));
    assert(updateTemplateRequest.getRequest().getURI().toString().equals(TEMPLATE_REQUEST_URL));
    assert(updateTemplateRequest.getRequestUrl().equals(TEMPLATE_REQUEST_URL));
    assert(updateTemplateRequest.getToken().equals(TOKEN));
  }

  @Test
  public void TestTemplateDeleteReqests() {
    DeleteTemplateRequest deleteTemplateRequest = new DeleteTemplateRequest.DeleteTemplateRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(deleteTemplateRequest.getTenantUuid().equals(TENANT_UUID));
    assert(deleteTemplateRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(deleteTemplateRequest.getRequestUri().equals("/tenants/" + TENANT_UUID + "/templates/" + TEMPLATE_UUID));
    assert(deleteTemplateRequest.getRequest().getMethod().equals("DELETE"));
    assert(deleteTemplateRequest.getRequest().getURI().toString().equals(TEMPLATE_REQUEST_URL));
    assert(deleteTemplateRequest.getRequestUrl().equals(TEMPLATE_REQUEST_URL));
    assert(deleteTemplateRequest.getToken().equals(TOKEN));
  }



}
