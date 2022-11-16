package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.TypesMem;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeService {

    private TypesMem typesMem;

    public List<AccidentType> findAll() {
        return typesMem.findAll();
    }

    public AccidentType findById(int id) {
        return typesMem.findById(id);
    }
}
