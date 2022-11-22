package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger key;

    private final AccidentType types = new AccidentType();
    private AccidentMem() {
        accidents.put(1, new Accident(1, "acc1", "text", "MSK"));
        accidents.put(2, new Accident(2, "acc2", "text", "SPB"));
        accidents.put(3, new Accident(3, "acc3", "text", "EKT"));
        key = new AtomicInteger(accidents.size());
    }
    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void addAccident(Accident accident) {
        accident.setId(key.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public void editAccident(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
