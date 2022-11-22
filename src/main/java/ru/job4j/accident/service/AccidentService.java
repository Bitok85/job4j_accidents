package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@AllArgsConstructor
@Service
public class AccidentService {
    private AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public void addAccident(Accident accident) {
        accidentMem.addAccident(accident);
    }

    public void editAccident(Accident accident) {
        accidentMem.editAccident(accident);
    }
}
