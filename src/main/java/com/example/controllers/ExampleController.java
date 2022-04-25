package com.example.controllers;

import com.example.models.Example;
import com.example.requests.example.ExamplePostRequestBody;
import com.example.requests.example.ExamplePutRequestBody;
import com.example.services.ExampleService;
import com.example.utils.DateUtils;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("examples")
@Log4j2
@RequiredArgsConstructor
public class ExampleController {

  private final ExampleService exampleService;
  private final DateUtils dateUtils;

  @GetMapping
  public ResponseEntity<List<Example>> getExample() {
    log.info(dateUtils.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
    return ResponseEntity.ok(exampleService.listAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Example> findById(@PathVariable long id) {
    return ResponseEntity.ok(exampleService.findByIdOrThrowBadRequestException(id));
  }

  @GetMapping("/find")
  public ResponseEntity<List<Example>> findByName(@RequestParam String name) {
    return ResponseEntity.ok(exampleService.findByName(name));
  }

  @PostMapping
  public ResponseEntity<Example> save(@RequestBody @Valid ExamplePostRequestBody examplePostRequestBody) {
    return new ResponseEntity<>(exampleService.save(examplePostRequestBody), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    exampleService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping
  public ResponseEntity<Void> replace(@RequestBody ExamplePutRequestBody examplePutRequestBody) {
    exampleService.replace(examplePutRequestBody);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
