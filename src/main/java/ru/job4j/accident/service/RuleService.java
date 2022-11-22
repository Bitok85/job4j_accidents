package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.List;

@Repository
@AllArgsConstructor
public class RuleService {

    private RuleMem ruleMem;

    public List<Rule> findAll() {
        return ruleMem.findAll();
    }

    public Rule findById(int id) {
        return ruleMem.findById(id);
    }
}
