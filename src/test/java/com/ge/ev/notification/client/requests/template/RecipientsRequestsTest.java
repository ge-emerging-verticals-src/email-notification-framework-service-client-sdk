package com.ge.ev.notification.client.requests.template;

import static com.ge.ev.notification.Constants.BASEURL;
import static com.ge.ev.notification.Constants.MATCHER_UUID;
import static com.ge.ev.notification.Constants.RECIPIENTS_JSON;
import static com.ge.ev.notification.Constants.RECIPIENTS_REQUEST_URI;
import static com.ge.ev.notification.Constants.RECIPIENTS_REQUEST_URL;
import static com.ge.ev.notification.Constants.RECIPIENT_UUID;
import static com.ge.ev.notification.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.Constants.TENANT_UUID;
import static com.ge.ev.notification.Constants.TOKEN;
import static com.ge.ev.notification.Constants.VERSION;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RecipientsRequestsTest {

  private final static String RECIPIENT = "dat.nguyen@notification.ge.com";

  @Test
  public void TestRecipientsRequests() {

    GetRecipientsRequest getRecipientsRequest = new GetRecipientsRequest.GetRecipientsRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setMatcherUuid(MATCHER_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(getRecipientsRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getRecipientsRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(getRecipientsRequest.getRequestUri().equals(RECIPIENTS_REQUEST_URI));
    assert(getRecipientsRequest.getRequest().getMethod().equals("GET"));
    assert(getRecipientsRequest.getRequest().getURI().toString().equals(RECIPIENTS_REQUEST_URL));
    assert(getRecipientsRequest.getRequestUrl().equals(RECIPIENTS_REQUEST_URL));
    assert(getRecipientsRequest.getToken().equals(TOKEN));
  }

  @Test
  public void TestCreateRecipientsRequests()
  {
    CreateRecipientsRequestBody createRecipientsRequestBody = new CreateRecipientsRequestBody.CreateRecipientsRequestBodyBuilder().addRecipient(RECIPIENT).build();
    assert(createRecipientsRequestBody != null);
    assert createRecipientsRequestBody.getRecipients().get(0).equals(RECIPIENT);
    assert(createRecipientsRequestBody.toJson().equals(RECIPIENTS_JSON));

    CreateRecipientsRequest createRecipientsRequest = new CreateRecipientsRequest.CreateRecipientsRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setCreateRecipientsRequestBody(createRecipientsRequestBody)
        .setTemplateUuid(TEMPLATE_UUID)
        .setMatcherUuid(MATCHER_UUID)
        .setToken(TOKEN)
        .build();

    assert(createRecipientsRequest.getTenantUuid().equals(TENANT_UUID));
    assert(createRecipientsRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(createRecipientsRequest.getRequestUri().equals(RECIPIENTS_REQUEST_URI));
    assert(createRecipientsRequest.getRequest().getMethod().equals("POST"));
    assert(createRecipientsRequest.getRequest().getURI().toString().equals(RECIPIENTS_REQUEST_URL));
    assert(createRecipientsRequest.getRequestUrl().equals(RECIPIENTS_REQUEST_URL));
    assert(createRecipientsRequest.getToken().equals(TOKEN));
    assert (createRecipientsRequest.getRequestBody().toJson().equals(RECIPIENTS_JSON));
  }

  @Test
  public void TestDeleteRecipientsRequest()
  {
    DeleteRecipientsRequest deleteRecipientsRequest = new DeleteRecipientsRequest.DeleteRecipientsRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setMatcherUuid(MATCHER_UUID)
        .setRecipientUuid(RECIPIENT_UUID)
        .setToken(TOKEN)
        .build();

    assert(deleteRecipientsRequest.getTenantUuid().equals(TENANT_UUID));
    assert(deleteRecipientsRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(deleteRecipientsRequest.getRequestUri().equals(RECIPIENTS_REQUEST_URI + "/" + RECIPIENT_UUID));
    assert(deleteRecipientsRequest.getRequest().getMethod().equals("DELETE"));
    assert(deleteRecipientsRequest.getRequest().getURI().toString().equals(RECIPIENTS_REQUEST_URL+ "/" + RECIPIENT_UUID));
    assert(deleteRecipientsRequest.getRequestUrl().equals(RECIPIENTS_REQUEST_URL+ "/" + RECIPIENT_UUID));
    assert(deleteRecipientsRequest.getToken().equals(TOKEN));
  }

}
