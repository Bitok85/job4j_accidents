package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHibernate;
import ru.job4j.accident.repository.RuleJdbcTemplate;
import ru.job4j.accident.repository.RuleMem;
import ru.job4j.accident.repository.RuleRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleService {

    private RuleRepository store;

    public List<Rule> findAll() {
        return store.findAll();
    }

    public Optional<Rule> findById(int id) {
        return store.findById(id);
    }
}
