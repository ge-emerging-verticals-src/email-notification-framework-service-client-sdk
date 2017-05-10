package com.ge.ev.notification.client.domain;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationTest {

  @Test
  public void TestConfiguration() {
    HashMap<String, Object> map = new HashMap<>();

    map.put("id",100L);
    map.put("uuid","137371bb-bb74-4d2e-9d67-6efb2818fcc4");
    map.put("tenantUuid","fc7ce689-994b-46ff-8158-d3a971264a55");
    map.put("protocol","smtp");
    map.put("host","smtp.test.notification.ge.com");
    map.put("port",587);
    map.put("smtpAuth",true);
    map.put("smtpStarttlsEnable",true);
    map.put("mailFrom","ev.notification.sample@notification.ge.com");
    map.put("mailUsername","ev.notification.sample@notification.ge.com");
    map.put("mailPassword","password123");
    map.put("timestamp",1494361319644L);
    map.put("lastUpdated",1494361319644L);
    map.put("mailReturnPath","return");


    Configuration configuration = Configuration.toObject(map);

    assert(configuration != null);
    assert(configuration.getId().equals(100L));
    assert(configuration.getUuid().equals("137371bb-bb74-4d2e-9d67-6efb2818fcc4"));
    assert(configuration.getTenantUuid().equals("fc7ce689-994b-46ff-8158-d3a971264a55"));
    assert(configuration.getProtocol().equals("smtp"));
    assert(configuration.getHost().equals("smtp.test.notification.ge.com"));
    assert(configuration.getPort().equals(587));
    assert(configuration.getSmtpAuth());
    assert(configuration.getSmtpStarttlsEnable());
    assert(configuration.getMailFrom().equals("ev.notification.sample@notification.ge.com"));
    assert(configuration.getMailUsername().equals("ev.notification.sample@notification.ge.com"));
    assert(configuration.getMailPassword().equals("password123"));
    assert(configuration.getTimestamp().equals(1494361319644L));
    assert(configuration.getLastUpdated().equals(1494361319644L));
    assert(configuration.getMailReturnPath().equals("return"));

  }

  @Test
  public void TestConfigurationBuilder() {

    Configuration configuration = new Configuration.ConfigurationBuilder()
        .setProtocol("smtp")
        .setHost("smtp.test.notification.ge.com")
        .setPort(587)
        .setSmtpAuth(true)
        .setSmtpStarttlsEnable(true)
        .setMailFrom("ev.notification.sample@notification.ge.com")
        .setMailUsername("ev.notification.sample@notification.ge.com")
        .setMailPassword("password123")
        .setMailReturnPath("return")
        .build();

    assert(configuration != null);
    assert(configuration.getProtocol().equals("smtp"));
    assert(configuration.getHost().equals("smtp.test.notification.ge.com"));
    assert(configuration.getPort().equals(587));
    assert(configuration.getSmtpAuth());
    assert(configuration.getSmtpStarttlsEnable());
    assert(configuration.getMailFrom().equals("ev.notification.sample@notification.ge.com"));
    assert(configuration.getMailUsername().equals("ev.notification.sample@notification.ge.com"));
    assert(configuration.getMailPassword().equals("password123"));
    assert(configuration.getMailReturnPath().equals("return"));

    String json = "{\"protocol\":\"smtp\",\"host\":\"smtp.test.notification.ge.com\",\"port\":587,\"smtpAuth\":true,\"smtpStarttlsEnable\":true,\"mailFrom\":\"ev.notification.sample@notification.ge.com\",\"mailUsername\":\"ev.notification.sample@notification.ge.com\",\"mailPassword\":\"password123\",\"mailReturnPath\":\"return\"}";

    assert(configuration.toJson().equals(json));

  }
}