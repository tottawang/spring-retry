package com.sample.servicename.resources;

import java.util.function.Supplier;

public class RetryableSampleComponentJava8 {

  private SampleComponent sampleComponent;

  public RetryableSampleComponentJava8(SampleComponent sampleComponent) {
    this.sampleComponent = sampleComponent;
  }

  public String sampleMethod() {
    return retry(() -> sampleComponent.sampleMethod(), 2, 5000);
  }

  public static <T> T retry(Supplier<T> function, int maxRetries, int backOffDelay) {
    int retryCounter = 0;
    Exception lastException = null;
    while (retryCounter < maxRetries) {
      try {
        return function.get();
      } catch (Exception e) {
        retryCounter++;
        if (retryCounter >= maxRetries) {
          break;
        }
        if (backOffDelay > 0) {
          try {
            Thread.sleep(backOffDelay);
          } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
          }
        }
      }
    }
    throw lastException instanceof RuntimeException ? ((RuntimeException) lastException)
        : new RuntimeException(lastException);
  }

}
