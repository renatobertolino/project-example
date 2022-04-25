package com.example.requests.example;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ExamplePostRequestBody {
  @NotEmpty(message = "The example cannot be empty")
  private String name;
//  @URL(message = "The URL is not valid")
//  private String url;
}
