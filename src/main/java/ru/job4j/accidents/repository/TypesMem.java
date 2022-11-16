package ru.job4j.accidents.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ThreadSafe
public class TypesMem {

    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public TypesMem() {
        types.put(1,  new AccidentType(1, "Two cars"));
        types.put(2, new AccidentType(2, "Car and man"));
        types.put(3, new AccidentType(3, "Car and bicycle"));
    }

    public List<AccidentType> findAll() {
        return types.values().stream().toList();
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }
}
