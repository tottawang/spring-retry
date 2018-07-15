package com.sample.servicename.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/sample")
public class SampleResource {

  @Autowired
  private RetryableSampleComponent shellComponent;

  @POST
  @Produces("text/plain")
  public String getSample() {
    return shellComponent.sampleMethod();
  }

}
