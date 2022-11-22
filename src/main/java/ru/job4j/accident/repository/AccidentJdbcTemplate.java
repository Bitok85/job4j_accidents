package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import javax.sql.DataSource;
import java.util.*;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;
    private final DataSource dataSource;

    public Accident addAccident(Accident accident) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("accident")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("id", accident.getId());
        params.put("name", accident.getName());
        params.put("text", accident.getText());
        params.put("address", accident.getAddress());
        accident.setId(simpleJdbcInsert.execute(params));
        return accident;
    }


    public List<Accident> findAll() {
        return jdbc.query("select id, name, text, address from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                });
    }

    public Accident findById(int id) {
        return jdbc.queryForObject("select * from accident where id = ?",
                new BeanPropertyRowMapper<>(Accident.class),
                id
        );
    }

    public void editAccident(Accident accident) {
        jdbc.update("update accident set name = ?, text = ?, address = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId());
    }

}
