package com.sample.servicename.resources;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class RetryableSampleComponent {

  private SampleComponent sampleComponent;

  public RetryableSampleComponent(SampleComponent sampleComponent) {
    this.sampleComponent = sampleComponent;
  }

  @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 5000))
  public String sampleMethod() {
    return this.sampleComponent.sampleMethod();
  }

}
