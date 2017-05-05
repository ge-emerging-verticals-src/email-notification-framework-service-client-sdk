package com.ge.ev.notification.test;

import static com.ge.ev.notification.test.Constants.AUTHORIZATION_HEADER;
import static com.ge.ev.notification.test.Constants.BASEURL;
import static com.ge.ev.notification.test.Constants.CONTENT_HEADER;
import static com.ge.ev.notification.test.Constants.TENANT_UUID;
import static com.ge.ev.notification.test.Constants.TOKEN;
import static com.ge.ev.notification.test.Constants.VERSION;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ge.ev.notification.client.requests.tenant.GetTenantRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequest;
import com.ge.ev.notification.client.requests.tenant.UpdateTenantConfigurationRequestBody;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by 212391398 on 4/19/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TenantRequestsTest {

  private static final String SUCCESS_WEBHOOK = "https://success.notification.predix.io";
  private static final String FAIL_WEBHOOK = "https://fail.notification.predix.io";

  private static final String REQUEST_URL = BASEURL + "/"  + VERSION + "/tenants/" + TENANT_UUID;

  @Test
  public void TestTenantRequestBuilder() throws JsonProcessingException {
    GetTenantRequest getTenantRequest = new GetTenantRequest.GetTenantRequestBuilder(BASEURL, VERSION, TENANT_UUID).setToken(TOKEN).build();
    assert(getTenantRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getTenantRequest.getRequestUri().equals("/tenants/" + TENANT_UUID));
    assert(getTenantRequest.getRequest().getMethod().equals("GET"));
    assert(getTenantRequest.getRequest().getURI().toString().equals(REQUEST_URL));
    assert(getTenantRequest.getRequestUrl().equals(REQUEST_URL));
    assert(getTenantRequest.getToken().equals(TOKEN));

    HttpRequestBase requestBase = getTenantRequest.getRequest();
    assert(requestBase!=null);

    assert(requestBase.getAllHeaders()[1].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[0].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(AUTHORIZATION_HEADER));

    UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody = new UpdateTenantConfigurationRequestBody.UpdateTenantConfigurationRequestBodyBuilder().setSuccessWebhook(SUCCESS_WEBHOOK).setFailWebhook(FAIL_WEBHOOK).build();
    assert(updateTenantConfigurationRequestBody.getFailWebhook().equals(FAIL_WEBHOOK));
    assert(updateTenantConfigurationRequestBody.getSuccessWebhook().equals(SUCCESS_WEBHOOK));

    UpdateTenantConfigurationRequest updateTenantConfigurationRequest = new UpdateTenantConfigurationRequest.UpdateTenantConfigurationRequestBuilder(BASEURL, VERSION, TENANT_UUID).setToken(TOKEN).setUpdateTenantConfigurationRequestBody(updateTenantConfigurationRequestBody).build();
    assert(updateTenantConfigurationRequest.getTenantUuid().equals(TENANT_UUID));
    assert(updateTenantConfigurationRequest.getRequestUri().equals("/tenants/" + TENANT_UUID));
    assert(updateTenantConfigurationRequest.getRequest().getMethod().equals("PUT"));
    assert(updateTenantConfigurationRequest.getRequest().getURI().toString().equals(REQUEST_URL));
    assert(updateTenantConfigurationRequest.getRequestBody() == updateTenantConfigurationRequestBody);
    assert(updateTenantConfigurationRequest.getRequestUrl().equals(REQUEST_URL));
    assert(updateTenantConfigurationRequest.getToken().equals(TOKEN));

    requestBase = updateTenantConfigurationRequest.getRequest();
    assert(requestBase!=null);

    assert(requestBase.getAllHeaders()[1].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[0].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(AUTHORIZATION_HEADER));

  }

}
