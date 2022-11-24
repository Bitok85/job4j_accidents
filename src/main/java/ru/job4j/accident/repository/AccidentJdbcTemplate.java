package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;
    private final DataSource dataSource;
    private final RuleJdbcTemplate ruleJdbcTemplate;

    private static final String ALL_ACCIDENTS
            = "SELECT DISTINCT a.id, a.name, a.text, a.address, accident_type_id"
            + " FROM accident a join accident_type a_t ON a.accident_type_id = a.accident_type_id";
    private static final String ACCIDENT_BY_ID = "SELECT * FROM accident WHERE id = ?";
    private static final String UPDATE_ACCIDENT
            = "UPDATE accident SET name = ?, text = ?, address = ?, accident_type_id = ? WHERE id = ?";
    private static final String DELETE_ACCIDENTS_RULES_BY_ACCIDENT
            = "DELETE FROM accidents_rules WHERE accident_id = ?";


    public Accident addAccident(Accident accident) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("accident")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<>();
        params.put("name", accident.getName());
        params.put("text", accident.getText());
        params.put("address", accident.getAddress());
        params.put("accident_type_id", accident.getType().getId());
        int accId = (int) simpleJdbcInsert.executeAndReturnKey(params);
        accident.setId(accId);
        ruleJdbcTemplate.addAccidentId(accident.getRules(), accId);
        return accident;
    }


    public List<Accident> findAll() {
        return jdbc.query(ALL_ACCIDENTS, new AccidentMapper());
    }

    public Accident findById(int id) {
        return jdbc.queryForObject(ACCIDENT_BY_ID, new BeanPropertyRowMapper<>(Accident.class), id);
    }

    public void editAccident(Accident accident) {
        jdbc.update(UPDATE_ACCIDENT,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update(DELETE_ACCIDENTS_RULES_BY_ACCIDENT, accident.getId());
        ruleJdbcTemplate.addAccidentId(accident.getRules(), accident.getId());
    }

    private class AccidentMapper implements RowMapper<Accident> {
        private int id = -1;

        public AccidentMapper() {
        }

        public AccidentMapper(int id) {
            this.id = id;
        }

        public Accident mapRow(ResultSet rs, int rowNum) throws SQLException {
            Accident accident = new Accident();
            if (id == -1) {
                accident.setId(rs.getInt("id"));
            } else {
                accident.setId(id);
            }
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            AccidentType accidentType = new AccidentType();
            accidentType.setId(rs.getInt("accident_type_id"));
            accidentType.setName(rs.getString("name"));
            accident.setType(accidentType);
            accident.setRules(ruleJdbcTemplate.findRulesByAccident(accident.getId()));
            return accident;
        }
    }

}
