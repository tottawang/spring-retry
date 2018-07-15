package com.sample.servicename.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.sample.servicename.resources.SampleResource;

@Configuration
public class Jersey extends ResourceConfig {

  public Jersey() {
    register(SampleResource.class);
  }
}
