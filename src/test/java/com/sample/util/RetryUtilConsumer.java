package com.sample.util;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.Assert;

public class RetryUtilConsumer {
	
	private RetryUtil retryUtil = new RetryUtil();
	private AtomicLong atomicCounter = new AtomicLong();
	private Long counter = Long.valueOf(1000L);
	
	public void getDataRetryable() {
		retryUtil.exceptionRetry(() -> doGetData());
		retryUtil.exceptionRetry(() -> doGetData());
	}
	
	private String doGetData() {
		String value = "fail";
		counter = counter + 1;
		/*if (counter.longValue() == 0) {
			return "fail";
		}
		counter.incrementAndGet();
		return "succeed";*/	
		Assert.isTrue(value.equals("succeed"));
		return "fail";
	}
}
