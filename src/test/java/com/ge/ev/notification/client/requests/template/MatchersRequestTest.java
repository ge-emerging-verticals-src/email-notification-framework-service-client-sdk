package com.ge.ev.notification.client.requests.template;

import static com.ge.ev.notification.Constants.ALL_MATCHERS_REQUEST_URL;
import static com.ge.ev.notification.Constants.BASEURL;
import static com.ge.ev.notification.Constants.MATCHER;
import static com.ge.ev.notification.Constants.MATCHERS_REQUEST_URI;
import static com.ge.ev.notification.Constants.MATCHERS_REQUEST_URL;
import static com.ge.ev.notification.Constants.MATCHER_UUID;
import static com.ge.ev.notification.Constants.TEMPLATE_UUID;
import static com.ge.ev.notification.Constants.TENANT_UUID;
import static com.ge.ev.notification.Constants.TOKEN;
import static com.ge.ev.notification.Constants.VERSION;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatchersRequestTest {

  @Test
  public void TestGetMatchers() {
    GetMatcherRequest getMatcherRequest = new GetMatcherRequest.GetMatcherRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setMatcherUuid(MATCHER_UUID)
        .setToken(TOKEN)
        .build();

    assert(getMatcherRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getMatcherRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(getMatcherRequest.getRequestUri().equals(MATCHERS_REQUEST_URI + "/" + MATCHER_UUID));
    assert(getMatcherRequest.getRequest().getMethod().equals("GET"));
    assert(getMatcherRequest.getRequest().getURI().toString().equals(MATCHERS_REQUEST_URL));
    assert(getMatcherRequest.getRequestUrl().equals(MATCHERS_REQUEST_URL));
    assert(getMatcherRequest.getToken().equals(TOKEN));

    getMatcherRequest = new GetMatcherRequest.GetMatcherRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(getMatcherRequest.getTenantUuid().equals(TENANT_UUID));
    assert(getMatcherRequest.getRequestUri().equals(MATCHERS_REQUEST_URI));
    assert(getMatcherRequest.getRequest().getMethod().equals("GET"));
    assert(getMatcherRequest.getRequest().getURI().toString().equals(ALL_MATCHERS_REQUEST_URL));
    assert(getMatcherRequest.getRequestUrl().equals(ALL_MATCHERS_REQUEST_URL));
    assert(getMatcherRequest.getToken().equals(TOKEN));
  }

   @Test
  public void TestCreateMatchers()
   {
     MatchersRequestBody matchersRequestBody = new MatchersRequestBody.MatchersRequestBodyBuilder(MATCHER)
         .build();

     assert(matchersRequestBody.getMatchers().equals(MATCHER));

     CreateMatchersRequest createMatchersRequest = new CreateMatchersRequest.CreateMatchersRequestBuilder(BASEURL, VERSION, TENANT_UUID)
         .setMatchersRequestBody(matchersRequestBody)
         .setMatcherUuid(MATCHER_UUID)
         .setTemplateUuid(TEMPLATE_UUID)
         .setToken(TOKEN)
         .build();

     assert(createMatchersRequest.getTenantUuid().equals(TENANT_UUID));
     assert(createMatchersRequest.getTemplateUuid().equals(TEMPLATE_UUID));
     assert(createMatchersRequest.getRequestUri().equals(MATCHERS_REQUEST_URI + "/" + MATCHER_UUID));
     assert(createMatchersRequest.getRequest().getMethod().equals("POST"));
     assert(createMatchersRequest.getRequest().getURI().toString().equals(MATCHERS_REQUEST_URL));
     assert(createMatchersRequest.getRequestUrl().equals(MATCHERS_REQUEST_URL));
     assert(createMatchersRequest.getToken().equals(TOKEN));
   }

  @Test
  public void TestUpdateMatchers()
  {
    MatchersRequestBody matchersRequestBody = new MatchersRequestBody.MatchersRequestBodyBuilder(MATCHER)
        .build();

    assert(matchersRequestBody.getMatchers().equals(MATCHER));

    UpdateMatcherRequest updateMatcherRequest = new UpdateMatcherRequest.UpdateMatcherRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setMatchersRequestBody(matchersRequestBody)
        .setMatcherUuid(MATCHER_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(updateMatcherRequest.getTenantUuid().equals(TENANT_UUID));
    assert(updateMatcherRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(updateMatcherRequest.getRequestUri().equals(MATCHERS_REQUEST_URI + "/" + MATCHER_UUID));
    assert(updateMatcherRequest.getRequest().getMethod().equals("PUT"));
    assert(updateMatcherRequest.getRequest().getURI().toString().equals(MATCHERS_REQUEST_URL));
    assert(updateMatcherRequest.getRequestUrl().equals(MATCHERS_REQUEST_URL));
    assert(updateMatcherRequest.getToken().equals(TOKEN));
  }

  @Test
  public void TestDeleteMatchers()
  {
    DeleteMatcherRequest deleteMatcherRequest = new DeleteMatcherRequest.DeleteMatchersRequestBuilder(BASEURL, VERSION, TENANT_UUID)
        .setMatcherUuid(MATCHER_UUID)
        .setTemplateUuid(TEMPLATE_UUID)
        .setToken(TOKEN)
        .build();

    assert(deleteMatcherRequest.getTenantUuid().equals(TENANT_UUID));
    assert(deleteMatcherRequest.getTemplateUuid().equals(TEMPLATE_UUID));
    assert(deleteMatcherRequest.getRequestUri().equals(MATCHERS_REQUEST_URI + "/" + MATCHER_UUID));
    assert(deleteMatcherRequest.getRequest().getMethod().equals("DELETE"));
    assert(deleteMatcherRequest.getRequest().getURI().toString().equals(MATCHERS_REQUEST_URL));
    assert(deleteMatcherRequest.getRequestUrl().equals(MATCHERS_REQUEST_URL));
    assert(deleteMatcherRequest.getToken().equals(TOKEN));
  }
}