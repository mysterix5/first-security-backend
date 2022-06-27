package de.neuefische.kanbanbackend.kanban;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final KanbanRepo kanbanRepo;

    public List<KanbanItem> findAll() {
        return kanbanRepo.findAll();
    }

    public Optional<KanbanItem> findById(String id) {
        return kanbanRepo.findById(id);
    }

    public KanbanItem save(KanbanItem newItem) {
        newItem.setId(UUID.randomUUID().toString());
        return kanbanRepo.save(newItem);
    }

    public Optional<KanbanItem> update(KanbanItem changedItem) {
        if (findById(changedItem.getId()).isPresent()){
            return Optional.of(kanbanRepo.save(changedItem));
        }
        return Optional.empty();
    }

    public Optional<KanbanItem> delete(String id) {
        Optional<KanbanItem> optDeleted = findById(id);
        if (optDeleted.isPresent()){
            kanbanRepo.deleteById(id);
            return optDeleted;
        }
        return Optional.empty();
    }
}
