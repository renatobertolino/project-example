package com.example.repository;

import com.example.models.Example;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {

  List<Example> findByName(String name);
}
