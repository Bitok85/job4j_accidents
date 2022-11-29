package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class AccidentHibernate {

    private static final Logger LOG = Logger.getLogger(Accident.class.getName());
    private final LocalCrudRepository crudRepository;
    private static final String FIND_ALL = "SELECT DISTINCT a FROM Accident a JOIN FETCH a.rules";
    private static final String FIND_BY_ID = "SELECT DISTINCT a FROM Accident a JOIN FETCH a.rules WHERE a.id = :aId";

    public Optional<Accident> addAccident(Accident accident) {
        try {
            crudRepository.run(session -> session.save(accident));
            return Optional.ofNullable(accident);
        } catch (Exception e) {
            LOG.error("Add accident error");
        }
        return Optional.empty();
    }

    public List<Accident> findAll() {
        return crudRepository.query(FIND_ALL, Accident.class);
    }

    public Optional<Accident> findById(int id) {
        return crudRepository.optional(FIND_BY_ID, Accident.class, Map.of("aId", id));
    }

    public void editAccident(Accident accident) {
        try {
            crudRepository.run(session -> session.update(accident));
        } catch (Exception e) {
            LOG.error("Accident update error");
        }
    }


}
