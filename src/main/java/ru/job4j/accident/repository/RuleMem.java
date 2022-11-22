package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ThreadSafe
public class RuleMem {
    private Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        rules.put(1, new Rule(1, "article1"));
        rules.put(2, new Rule(2, "article2"));
        rules.put(3, new Rule(3, "article3"));
    }

    public List<Rule> findAll() {
        return rules.values().stream().toList();
    }

    public Rule findById(int id) {
        return rules.get(id);
    }
}
