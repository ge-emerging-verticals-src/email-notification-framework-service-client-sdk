package com.ge.ev.notification.test;

import com.ge.ev.notification.client.domain.Tenant;
import java.util.LinkedHashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/1/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestTenant {

  private static final String json = "{\"timestamp\":1491854770254,\"id\":1,\"successWebhook\":\"https://notification-webhook.run.aws-usw02-pr.ice.predix.io/success\",\"monthlyLimit\":2000000,\"bindingId\":\"35e85d72-828e-464a-ae91-8aa70b48b2cc\",\"trustedIssuers\":\"https://predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token\",\"dailyLimit\":10000,\"planId\":\"668cbf71-404a-4ddb-9da3-45c8cdac5f3f\",\"uuid\":\"c96d9e70-91a7-4f54-ab4d-28f636e3f590\",\"failWebhook\":\"https://notification-webhook.run.aws-usw02-pr.ice.predix.io/fail\"}";
  @Test
  public void TenantBaseTest() {

    Tenant tenant = Tenant.toObject(json, Tenant.class);

    assert(tenant.toJson().equals(json));
    assert(tenant.getTimestamp()==1491854770254L);
    assert(tenant.getId()==1L);
    assert(tenant.getBindingId().equals("35e85d72-828e-464a-ae91-8aa70b48b2cc"));
    assert(tenant.getUuid().equals("c96d9e70-91a7-4f54-ab4d-28f636e3f590"));
    assert(tenant.getPlanId().equals("668cbf71-404a-4ddb-9da3-45c8cdac5f3f"));
    assert(tenant.getTrustedIssuers().equals("https://predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token"));
    assert(tenant.getDailyLimit()==10000L);
    assert(tenant.getMonthlyLimit()==2000000L);
    assert(tenant.getSuccessWebhook().equals("https://notification-webhook.run.aws-usw02-pr.ice.predix.io/success"));
    assert(tenant.getFailWebhook().equals("https://notification-webhook.run.aws-usw02-pr.ice.predix.io/fail"));

    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
    map.put("timestamp",1491854770254L);
    map.put("id",1L);
    map.put("bindingId","35e85d72-828e-464a-ae91-8aa70b48b2cc");
    map.put("uuid","c96d9e70-91a7-4f54-ab4d-28f636e3f590");
    map.put("planId","668cbf71-404a-4ddb-9da3-45c8cdac5f3f");
    map.put("trustedIssuers","https://predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token");
    map.put("dailyLimit",10000L);
    map.put("monthlyLimit",2000000L);
    map.put("successWebhook","https://notification-webhook.run.aws-usw02-pr.ice.predix.io/success");
    map.put("failWebhook","https://notification-webhook.run.aws-usw02-pr.ice.predix.io/fail");

    tenant = Tenant.toObject(map, Tenant.class);

    assert(tenant.getTimestamp()==1491854770254L);
    assert(tenant.getId()==1L);
    assert(tenant.getBindingId().equals("35e85d72-828e-464a-ae91-8aa70b48b2cc"));
    assert(tenant.getUuid().equals("c96d9e70-91a7-4f54-ab4d-28f636e3f590"));
    assert(tenant.getPlanId().equals("668cbf71-404a-4ddb-9da3-45c8cdac5f3f"));
    assert(tenant.getTrustedIssuers().equals("https://predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token"));
    assert(tenant.getDailyLimit()==10000L);
    assert(tenant.getMonthlyLimit()==2000000L);
    assert(tenant.getSuccessWebhook().equals("https://notification-webhook.run.aws-usw02-pr.ice.predix.io/success"));
    assert(tenant.getFailWebhook().equals("https://notification-webhook.run.aws-usw02-pr.ice.predix.io/fail"));
  }

}
