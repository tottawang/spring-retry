package com.sample.util;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.ws.rs.core.Response;

public class RetryUtil {

  public static final int BACKOFF_DELAY = 5000;
  public static final int MAX_ATTEMPTS = 2;

  public static class ExecutionResult<T> {
    public T result;
    public Throwable error;
  }

  public Response httpRetry(Supplier<Response> function) {
    return retryable(function, HTTP_RETRY_TESTER);
  }

  public <T> T exceptionRetry(Supplier<T> function) {
    return retryable(function, EXCEPTION_RETRY_TESTER::apply);
  }

  public <T> T retryable(Supplier<T> supplier, Function<ExecutionResult<T>, Boolean> shouldRetry) {
    int retryCount = 0;
    ExecutionResult<T> executionResult = new ExecutionResult<>();
    while (retryCount < MAX_ATTEMPTS) {
      try {
        executionResult.result = supplier.get();
      } catch (Throwable throwable) {
        executionResult.error = throwable;
      }
      retryCount++;
      if (shouldRetry.apply(executionResult)) {
        backOffDelay(BACKOFF_DELAY);
      } else
        break;
    }
    return executionResult.result;
  }

  public static final Function<ExecutionResult<Response>, Boolean> HTTP_RETRY_TESTER = (er) -> {
    if (null != er.result) {
      return er.result.getStatus() >= 500;
    }
    return false;
  };

  public static final Function<ExecutionResult, Boolean> EXCEPTION_RETRY_TESTER =
      (er) -> null != er.error;

  private void backOffDelay(int backOffDelay) {
    if (backOffDelay > 0) {
      try {
        Thread.sleep(backOffDelay);
      } catch (InterruptedException ie) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
