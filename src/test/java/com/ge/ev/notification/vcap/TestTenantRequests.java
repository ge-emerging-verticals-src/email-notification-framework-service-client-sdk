package com.ge.ev.notification.vcap;

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
public class TestTenantRequests {

  private static final String BASEURL = "https://notification.predix.io";
  private static final String VERSION = "v1";
  private static final String TENANT_UUID = "2beb4e8d-7eba-4cc1-b584-3d95bf84aedc";
  private static final String SUCCESS_WEBHOOK = "https://success.notification.predix.io";
  private static final String FAIL_WEBHOOK = "https://fail.notification.predix.io";
  private static final String TOKEN = "8536aed6-0422-4e53-bb7d-4fc967128c50";

  private static final String REQUEST_URL = BASEURL + "/"  + VERSION + "/tenants/" + TENANT_UUID;
  private static final String CONTENT_HEADER = "application/json";
  private static final String AUTHORIZATION_HEADER = "bearer " + TOKEN;

  @Test
  public void TenantRequestBuilderTest() throws JsonProcessingException {
    GetTenantRequest getTenantRequest = new GetTenantRequest.GetTenantRequestBuilder(BASEURL, VERSION, TENANT_UUID).setToken(TOKEN).build();
    assert(getTenantRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getTenantRequest.getRequestUri().equals("/tenants/" + TENANT_UUID));
    assert(getTenantRequest.getRequest().getMethod().equals("GET"));
    assert(getTenantRequest.getRequest().getURI().toString().equals(REQUEST_URL));
    assert(getTenantRequest.getRequestUrl().equals(REQUEST_URL));
    assert(getTenantRequest.getToken().equals(TOKEN));

    HttpRequestBase requestBase = getTenantRequest.getRequest();
    assert(requestBase!=null);

    assert(requestBase.getAllHeaders()[0].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[1].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(AUTHORIZATION_HEADER));

    UpdateTenantConfigurationRequestBody updateTenantConfigurationRequestBody = new UpdateTenantConfigurationRequestBody.UpdateTenantConfigurationRequestBodyBuilder().setSuccessWebhook(SUCCESS_WEBHOOK).setFailWebhook(FAIL_WEBHOOK).build();
    assert(updateTenantConfigurationRequestBody.getFailWebhook().equals(FAIL_WEBHOOK));
    assert(updateTenantConfigurationRequestBody.getSuccessWebhook().equals(SUCCESS_WEBHOOK));

    UpdateTenantConfigurationRequest updateTenantConfigurationRequest = new UpdateTenantConfigurationRequest.UpdateTenantConfigurationRequestBuilder(BASEURL, VERSION, TENANT_UUID).setToken(TOKEN).setUpdateTenantConfigurationRequestBody(updateTenantConfigurationRequestBody).build();
    assert(updateTenantConfigurationRequest.getTenantUuid().equals(TENANT_UUID));
    assert(updateTenantConfigurationRequest.getRequestUri().equals("/tenants/" + TENANT_UUID));
    assert(updateTenantConfigurationRequest.getRequest().getMethod().equals("POST"));
    assert(updateTenantConfigurationRequest.getRequest().getURI().toString().equals(REQUEST_URL));
    assert(updateTenantConfigurationRequest.getRequestBody() == updateTenantConfigurationRequestBody);
    assert(updateTenantConfigurationRequest.getRequestUrl().equals(REQUEST_URL));
    assert(updateTenantConfigurationRequest.getToken().equals(TOKEN));

    requestBase = updateTenantConfigurationRequest.getRequest();
    assert(requestBase!=null);

    assert(requestBase.getAllHeaders()[0].getName().equals("Content-Type"));
    assert(requestBase.getAllHeaders()[0].getValue().equals(CONTENT_HEADER));
    assert(requestBase.getAllHeaders()[1].getName().equals("Authorization"));
    assert(requestBase.getAllHeaders()[1].getValue().equals(AUTHORIZATION_HEADER));

  }

}
