package com.ge.ev.notification.test;

import static com.ge.ev.notification.test.Constants.BASEURL;
import static com.ge.ev.notification.test.Constants.MATCHER_UUID;
import static com.ge.ev.notification.test.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.test.Constants.TENANT_UUID;
import static com.ge.ev.notification.test.Constants.TOKEN;
import static com.ge.ev.notification.test.Constants.VERSION;

import com.ge.ev.notification.client.requests.template.CreateRecipientsRequest;
import com.ge.ev.notification.client.requests.template.CreateRecipientsRequestBody;
import com.ge.ev.notification.client.requests.template.GetRecipientsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/4/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class RecipientsRequestsTest {

  @Test
  public void TestRecipientsRequests() {

    GetRecipientsRequest getRecipientsRequest = new GetRecipientsRequest.GetRecipientsRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setMatcherUuid(MATCHER_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    CreateRecipientsRequest createRecipientsRequest = new CreateRecipientsRequest.CreateRecipientsRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setCreateRecipientsRequestBody(new CreateRecipientsRequestBody.CreateRecipientsRequestBodyBuilder().build())
        .setTemplateUuid(TEMPLATE_UUID)
        .setMatcherUuid(MATCHER_UUID)
        .setToken(TOKEN)
        .build();

    System.out.println(createRecipientsRequest.getRequest().getURI().toString());


    CreateRecipientsRequestBody createRecipientsRequestBody = new CreateRecipientsRequestBody.CreateRecipientsRequestBodyBuilder().addRecipient("dat.nguyen@notification.ge.com").build();
  }

}
