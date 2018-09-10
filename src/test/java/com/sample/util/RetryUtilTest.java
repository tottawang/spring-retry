package com.sample.util;

import org.junit.Test;

public class RetryUtilTest {
	
	@Test
	public void testRetryUtil() {
		RetryUtilConsumer consumer = new RetryUtilConsumer();
		consumer.getDataRetryable();
	}

}
