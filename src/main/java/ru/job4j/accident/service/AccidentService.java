package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccidentService {
    private AccidentRepository store;

    public List<Accident> findAll() {
        return store.findAll();
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    public void addAccident(Accident accident) {
        store.save(accident);
    }

    public void editAccident(Accident accident) {
        store.save(accident);
    }
}
