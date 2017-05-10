package com.ge.ev.notification.client.requests.template;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;

/**
 * Created by 212391398 on 5/4/17.
 */
public class UploadTemplateRequest extends TemplateRequest {

  private static final String FILE_PARAMETER_KEY = "file";
  private static final String UPLOAD_TEMPLATE_URI =  "%s/upload";
  private static final String TMP_FILENAME = "template.txt";

  private UploadTemplateRequestBody uploadTemplateRequestBody;

  protected UploadTemplateRequest(UploadTemplateRequestBuilder builder) {
    super(builder);
    
    HttpPost post = new HttpPost(getRequestUrl());

    byte[] bytes = null;
    try {
      bytes = IOUtils.toString(builder.getUploadTemplateRequestBody().getTemplateFileInputStream(), "UTF-8").getBytes();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    HttpEntity entity = MultipartEntityBuilder
        .create()
        .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
        .addPart(FILE_PARAMETER_KEY, new ByteArrayBody(bytes, ContentType.MULTIPART_FORM_DATA, TMP_FILENAME))
        .build();

    this.notificationRequestBody =  this.uploadTemplateRequestBody = builder.getUploadTemplateRequestBody();
    post.setEntity( entity );
    super.requestBase = post;
  }

  @Override
  public String getRequestUri()
  {
    if (requestUri == null)
    {
      requestUri = String.format(UPLOAD_TEMPLATE_URI, super.getRequestUri());
    }
    return requestUri;
  }

  public static class UploadTemplateRequestBuilder extends TemplateRequestBuilder
  {
    private UploadTemplateRequestBody uploadTemplateRequestBody;

    public UploadTemplateRequestBuilder( String baseUrl, String version, String tenantUuid )
    {
      super(baseUrl, version, tenantUuid);
    }

    public UploadTemplateRequestBody getUploadTemplateRequestBody() {
      return uploadTemplateRequestBody;
    }

    public UploadTemplateRequestBuilder setUploadTemplateRequestBody(
        UploadTemplateRequestBody uploadTemplateRequestBody) {
      this.uploadTemplateRequestBody = uploadTemplateRequestBody;
      return this;
    }

    public UploadTemplateRequestBuilder setTemplateUuid(String templateUuid) {
      this.templateUuid = templateUuid;
      return this;
    }

    public UploadTemplateRequestBuilder setToken(String token) {
      this.token = token;
      return this;
    }

    public UploadTemplateRequest build()
    {
      return new UploadTemplateRequest(this);
    }
  }

}
