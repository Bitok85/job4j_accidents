package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class TypeHibernate {
    private final LocalCrudRepository localCrudRepository;
    private static final String FIND_ALL = "FROM AccidentType";
    private static final String FIND_BY_ID = "FROM AccidentType WHERE :tId = id";

    public List<AccidentType> findAll() {
        return localCrudRepository.query(FIND_ALL, AccidentType.class);
    }

    public Optional<AccidentType> findById(int id) {
        return localCrudRepository.optional(FIND_BY_ID, AccidentType.class, Map.of("tId", id));
    }



}
