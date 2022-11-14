package ru.job4j.accidents.repo;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ThreadSafe
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private AccidentMem() {
        accidents.put(1, new Accident(1, "acc1", "text", "MSK"));
        accidents.put(2, new Accident(2, "acc2", "text", "SPB"));
        accidents.put(3, new Accident(3, "acc3", "text", "EKT"));
    }

    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }
}
