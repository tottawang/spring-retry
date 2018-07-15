package com.sample.servicename.resources;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class SampleResourceTest {

  private SampleResource shellResource;

  @Mock
  private SampleComponent shellComponent;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    shellResource = new SampleResource();
    ReflectionTestUtils.setField(shellResource, "shellComponent", shellComponent);
  }

  @Test
  public void getSampleTest() {

  }

}
