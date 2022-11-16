package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentMem;

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
