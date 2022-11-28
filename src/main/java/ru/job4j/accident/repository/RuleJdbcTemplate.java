package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RuleJdbcTemplate {

    private final JdbcTemplate jdbc;
    private final DataSource dataSource;
    private static final String RULES_BY_ACCIDENT
            = "SELECT r.id id, r.name name FROM accidents_rules a JOIN rule r ON r.id = a.rule_id"
            + " WHERE accident_id = ?";
    private static final String RULE_BY_ID = "SELECT * FROM rule WHERE id = ?";
    private static final String ALL_RULES = "SELECT * FROM rule";

    public List<Rule> findAll() {
        return jdbc.query(ALL_RULES, new BeanPropertyRowMapper<>(Rule.class));
    }

    public Rule findById(int id) {
        return jdbc.queryForObject(RULE_BY_ID, new BeanPropertyRowMapper<>(Rule.class), id);
    }

    public void addAccidentId(Set<Rule> rules, int accId) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("accidents_rules")
                .usingGeneratedKeyColumns("id");
        rules.forEach(rule -> {
            Map<String, Object> params = new HashMap<>();
            params.put("accident_id", accId);
            params.put("rule_id", rule.getId());
            jdbcInsert.execute(params);
        });
    }

    public Set<Rule> findRulesByAccident(int accId) {
        return new HashSet<>(jdbc.query(RULES_BY_ACCIDENT, new BeanPropertyRowMapper<>(Rule.class), accId));
    }

}
