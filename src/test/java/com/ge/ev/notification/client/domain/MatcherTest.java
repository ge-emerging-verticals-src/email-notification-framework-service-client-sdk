package com.ge.ev.notification.client.domain;

import static com.ge.ev.notification.Constants.MATCHER_JSON;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatcherTest {
  @Test
  public void TestMatcher() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("matchers", "$.[?(@.alert in ['HIGH'])]");
    map.put("createdDate", 1494362598479L);
    map.put("templateUuid", "a2042b41-3f92-487a-a777-3086c5271191");
    map.put("matchersUuid", "267b4831-d628-4486-a72f-b85551fb76f3");

    Matcher matcher = Matcher.toObject(map);
    assert(matcher != null);
    assert(matcher.getMatchers().equals("$.[?(@.alert in ['HIGH'])]"));
    assert(matcher.getCreatedDate().equals(1494362598479L));
    assert(matcher.getTemplateUuid().equals("a2042b41-3f92-487a-a777-3086c5271191"));
    assert(matcher.getMatchersUuid().equals("267b4831-d628-4486-a72f-b85551fb76f3"));
    assert(matcher.toJson().equals(MATCHER_JSON));
  }
}
