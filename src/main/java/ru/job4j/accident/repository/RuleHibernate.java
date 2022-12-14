package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class RuleHibernate {

    private final LocalCrudRepository localCrudRepository;
    private static final String FIND_ALL = "FROM Rule";
    private static final String FIND_BY_ID = "FROM Rule WHERE :rId = id";

    public List<Rule> findAll() {
        return localCrudRepository.query(FIND_ALL, Rule.class);
    }

    public Optional<Rule> findById(int id) {
        return localCrudRepository.optional(FIND_BY_ID, Rule.class, Map.of("rId", id));
    }
}
