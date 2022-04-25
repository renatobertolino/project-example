package com.example.services;

import com.example.exception.BadRequestException;
import com.example.mapper.ExampleMapper;
import com.example.models.Example;
import com.example.repository.ExampleRepository;
import com.example.requests.example.ExamplePostRequestBody;
import com.example.requests.example.ExamplePutRequestBody;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ExampleService {

  private final ExampleRepository exampleRepository;

  public List<Example> listAll() {
    return exampleRepository.findAll();
  }

  public List<Example> findByName(String name) {
    return exampleRepository.findByName(name);
  }
  public Example findByIdOrThrowBadRequestException(Long id) {
    return exampleRepository
        .findById(id)
        .orElseThrow(
            () -> new BadRequestException("Example not found"));
  }

  public Example save(ExamplePostRequestBody examplePostRequestBody) {
    return exampleRepository.save(ExampleMapper.INSTANCE.toExample(examplePostRequestBody));
  }

  public void delete(long id) {
    exampleRepository.delete(findByIdOrThrowBadRequestException(id));
  }

  public void replace(ExamplePutRequestBody examplePutRequestBody) {
    Example savedExample = findByIdOrThrowBadRequestException(examplePutRequestBody.getId());

    Example example = ExampleMapper.INSTANCE.toExample(examplePutRequestBody);
    example.setId(savedExample.getId());

    exampleRepository.save(example);
  }
}
