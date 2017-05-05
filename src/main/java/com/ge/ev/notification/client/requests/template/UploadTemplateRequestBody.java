package com.ge.ev.notification.client.requests.template;

import com.ge.ev.notification.client.json.JsonObject;
import com.ge.ev.notification.client.requests.NotificationRequestBody;
import java.io.InputStream;

/**
 * Created by 212391398 on 5/4/17.
 */
public class UploadTemplateRequestBody extends JsonObject implements NotificationRequestBody {

  private InputStream templateFileInputStream;

  private  UploadTemplateRequestBody(UploadTemplateRequestBodyBuilder uploadTemplateRequestBodyBuilder)
  {
    templateFileInputStream  = uploadTemplateRequestBodyBuilder.getTemplateFileInputStream();
  }

  public InputStream getTemplateFileInputStream() {
    return templateFileInputStream;
  }

  public static class UploadTemplateRequestBodyBuilder
  {
    private InputStream templateFileInputStream;

    public UploadTemplateRequestBodyBuilder(InputStream templateFileInputStream) {
      this.templateFileInputStream = templateFileInputStream;
    }
    public InputStream getTemplateFileInputStream() {
      return templateFileInputStream;
    }

    public UploadTemplateRequestBody build()
    {
      return new UploadTemplateRequestBody(this);
    }
  }
}
