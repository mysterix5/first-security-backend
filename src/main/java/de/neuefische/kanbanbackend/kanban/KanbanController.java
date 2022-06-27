package de.neuefische.kanbanbackend.kanban;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kanban")
@RequiredArgsConstructor
public class KanbanController {


    private final KanbanService kanbanService;

    @GetMapping
    public List<KanbanItem> getAll(){
        return kanbanService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KanbanItem> getItemById(@PathVariable String id){
        return ResponseEntity.of(kanbanService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KanbanItem postNewItem(@RequestBody KanbanItem newItem){
        return kanbanService.save(newItem);
    }

    @PutMapping
    public ResponseEntity<KanbanItem> updateKanbanItem(@RequestBody KanbanItem changedItem){
        return ResponseEntity.of(kanbanService.update(changedItem));
    }

    @PutMapping("/next")
    public ResponseEntity<KanbanItem> advanceItem(@RequestBody KanbanItem changedItem){
        if (changedItem.getStatus().equals(KanbanStatus.OPEN)){
            changedItem.setStatus(KanbanStatus.IN_PROGRESS);
        } else {
            changedItem.setStatus(KanbanStatus.DONE);
        }
        return ResponseEntity.of(kanbanService.update(changedItem));
    }

    @PutMapping("/prev")
    public ResponseEntity<KanbanItem> returnItem(@RequestBody KanbanItem changedItem){
        if (changedItem.getStatus().equals(KanbanStatus.DONE)){
            changedItem.setStatus(KanbanStatus.IN_PROGRESS);
        } else {
            changedItem.setStatus(KanbanStatus.OPEN);
        }
        return ResponseEntity.of(kanbanService.update(changedItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<KanbanItem> deleteItemByID(@PathVariable String id){
        return ResponseEntity.of(kanbanService.delete(id));
    }

}
