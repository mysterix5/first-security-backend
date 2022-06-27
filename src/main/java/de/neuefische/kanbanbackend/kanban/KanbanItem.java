package de.neuefische.kanbanbackend.kanban;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KanbanItem {

    private String id;
    private String task;
    private String description;
    private KanbanStatus status;

}
