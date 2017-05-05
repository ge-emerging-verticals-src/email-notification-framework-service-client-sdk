package com.ge.ev.notification.client.requests.template;

import java.io.UnsupportedEncodingException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;

/**
 * Created by 212391398 on 5/4/17.
 */
public class UpdateTemplateRequest extends CreateTemplateRequest {

  protected UpdateTemplateRequest(CreateTemplateRequestBuilder builder) {
    super(builder);

    this.headers.put(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
    HttpPatch patch= new HttpPatch(getRequestUrl());
    this.createTemplateRequestBody = builder.getCreateTemplateRequestBody();

    try {
      patch.setEntity(new StringEntity( this.createTemplateRequestBody.toJson() ));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    super.requestBase = patch;
  }

}
