package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuleRepository extends CrudRepository<Rule, Integer> {

    Optional<Rule> findById(int id);

    List<Rule> findAll();
}
