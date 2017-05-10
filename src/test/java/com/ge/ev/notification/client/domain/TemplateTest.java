package com.ge.ev.notification.client.domain;

import static com.ge.ev.notification.Constants.TEMPLATE_FILE;
import static com.ge.ev.notification.Constants.TEMPLATE_JSON;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by 212391398 on 5/10/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TemplateTest {

  @Test
  public void TestTemplateTest() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("domain", "ev.notification.ge.com");
    map.put("name", "usage_alert");
    map.put("description", "Usage alert template");
    map.put("notificationType", "EMAIL");
    map.put("interval", 0);
    map.put("expiry", 0);
    map.put("templateType", "THYMELEAF");
    map.put("templateUuid", "a2042b41-3f92-487a-a777-3086c5271191");
    map.put("subjectTemplate", "Usage Report");
    map.put("bodyTemplate", TEMPLATE_FILE);

    Template template = Template.toObject(map);
    assert(template != null);
    assert(template.getDomain().equals( "ev.notification.ge.com"));
    assert(template.getName().equals("usage_alert"));
    assert(template.getDescription().equals("Usage alert template"));
    assert(template.getNotificationType().equals("EMAIL"));
    assert(template.getInterval()==0);
    assert(template.getExpiry()==0);
    assert(template.getTemplateType().equals("THYMELEAF"));
    assert(template.getTemplateUuid().equals("a2042b41-3f92-487a-a777-3086c5271191"));
    assert(template.getSubjectTemplate().equals("Usage Report"));
    assert(template.getBodyTemplate().equals(TEMPLATE_FILE));
    assert(template.toJson().equals(TEMPLATE_JSON));
  }

}
