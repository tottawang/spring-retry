package com.sample.servicename.resources;

import org.springframework.stereotype.Component;

@Component
public class SampleComponent {

  private String value;
  private static int index = 0;

  public SampleComponent() {}

  public SampleComponent(String value) {
    this.value = value;
  }

  public String sampleMethod() {
    System.out.println("Method gets called!");
    index++;
    if (index % 2 != 0) {
      throw new RuntimeException("operation is failing");
    }
    return value;
  }

}
