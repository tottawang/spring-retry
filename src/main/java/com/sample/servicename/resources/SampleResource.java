package com.sample.servicename.resources;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/sample")
public class SampleResource {

  @Autowired
  private RetryableSampleComponent shellComponent;

  @POST
  @Produces("text/plain")
  public String testSpringRetry() {
    return shellComponent.sampleMethod();
  }

  @PUT
  @Produces("text/plain")
  public String testJava8Retry() {
    RetryableSampleComponentJava8 retryableSampleComponent =
        new RetryableSampleComponentJava8(new SampleComponent());
    return retryableSampleComponent.sampleMethod();
  }

}
