package com.example.mapper;

import com.example.models.Example;
import com.example.requests.example.ExamplePostRequestBody;
import com.example.requests.example.ExamplePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ExampleMapper {
  public static final ExampleMapper INSTANCE = Mappers.getMapper(ExampleMapper.class);

  public abstract Example toExample(ExamplePostRequestBody examplePostRequestBody);

  public abstract Example toExample(ExamplePutRequestBody examplePutRequestBody);
}
