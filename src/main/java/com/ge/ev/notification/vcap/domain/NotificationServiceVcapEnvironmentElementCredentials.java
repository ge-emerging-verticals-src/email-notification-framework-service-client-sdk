package com.ge.ev.notification.vcap.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ge.ev.notification.client.json.JsonObject;
import java.util.HashMap;

/**
 * Created by 212391398 on 4/18/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationServiceVcapEnvironmentElementCredentials extends JsonObject
{
  private String zoneOauthScope;

  private String tenantUuid;

  private String catalogUri;

  private String version;

  private String trustedIssuerIds;

  public String getZoneOauthScope ()
  {
    return zoneOauthScope;
  }

  public void setZoneOauthScope (String zoneOauthScope)
  {
    this.zoneOauthScope = zoneOauthScope;
  }

  public String getTenantUuid ()
  {
    return tenantUuid;
  }

  public void setTenantUuid (String tenantUuid)
  {
    this.tenantUuid = tenantUuid;
  }

  public String getCatalogUri ()
  {
    return catalogUri;
  }

  public void setCatalogUri (String catalogUri)
  {
    this.catalogUri = catalogUri;
  }

  public String getVersion ()
  {
    return version;
  }

  public void setVersion (String version)
  {
    this.version = version;
  }

  public String getTrustedIssuerIds ()
  {
    return trustedIssuerIds;
  }

  public void setTrustedIssuerIds (String trustedIssuerIds)
  {
    this.trustedIssuerIds = trustedIssuerIds;
  }

  public static NotificationServiceVcapEnvironmentElementCredentials toObject(String json)
  {
    return JsonObject.toObject(json, NotificationServiceVcapEnvironmentElementCredentials.class);
  }

  public static NotificationServiceVcapEnvironmentElementCredentials toObject(HashMap<String, Object> map)
  {
    return JsonObject.toObject(map, NotificationServiceVcapEnvironmentElementCredentials.class);
  }

}
