package com.ge.ev.notification.test;

import static com.ge.ev.notification.test.Constants.BASEURL;
import static com.ge.ev.notification.test.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.test.Constants.TENANT_UUID;
import static com.ge.ev.notification.test.Constants.TOKEN;
import static com.ge.ev.notification.test.Constants.VERSION;

import com.ge.ev.notification.client.requests.template.GetTemplateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/3/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TemplateRequestsTest {

  private static final String ALL_TEMPLATE_REQUEST_URL = BASEURL + "/" + VERSION + "/tenants/" + TENANT_UUID + "/templates";
  private static final String TEMPLATE_REQUEST_URL = ALL_TEMPLATE_REQUEST_URL + "/" + TEMPLATE_UUID;

  @Test
  public void TestTemplateRequestsTest() {
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

}
