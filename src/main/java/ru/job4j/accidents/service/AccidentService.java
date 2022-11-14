package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repo.AccidentMem;

import java.util.List;

@AllArgsConstructor
@Service
public class AccidentService {
    private AccidentMem accidentMem;

    public List<Accident> findAll() {
        return accidentMem.findAll();
    }
}
