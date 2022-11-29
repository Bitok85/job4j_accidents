package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends CrudRepository<AccidentType, Integer> {

    Optional<AccidentType> findById(int id);

    List<AccidentType> findAll();
}
