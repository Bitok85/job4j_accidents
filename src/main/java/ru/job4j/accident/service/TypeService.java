package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.TypeHibernate;
import ru.job4j.accident.repository.TypeJdbcTemplate;
import ru.job4j.accident.repository.TypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TypeService {

    private TypeRepository store;

    public List<AccidentType> findAll() {
        return store.findAll();
    }

    public Optional<AccidentType> findById(int id) {
        return store.findById(id);
    }
}
