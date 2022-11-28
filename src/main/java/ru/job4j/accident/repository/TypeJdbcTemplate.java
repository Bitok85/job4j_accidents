package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@AllArgsConstructor
public class TypeJdbcTemplate {

    private final JdbcTemplate jdbc;

    public List<AccidentType> findAll() {
        return jdbc.query("select * from accident_type", new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public AccidentType findById(int id) {
        return jdbc.queryForObject("select * from accident_type where id = ?",
                new BeanPropertyRowMapper<>(AccidentType.class), id);
    }
}
