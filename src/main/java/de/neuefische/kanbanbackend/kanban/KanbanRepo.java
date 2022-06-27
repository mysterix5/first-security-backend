package de.neuefische.kanbanbackend.kanban;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class KanbanRepo  {

    private final Map<String, KanbanItem> allTasks = new HashMap<>();

    public List<KanbanItem> findAll() {
        return allTasks.values().stream().toList();
    }

    public Optional<KanbanItem> findById(String id) {
        return Optional.of(allTasks.get(id));
    }

    public KanbanItem save(KanbanItem newItem) {
        allTasks.put(newItem.getId(),newItem);
        return newItem;
    }

    public void deleteById(String id) {
        allTasks.remove(id);
    }
}
