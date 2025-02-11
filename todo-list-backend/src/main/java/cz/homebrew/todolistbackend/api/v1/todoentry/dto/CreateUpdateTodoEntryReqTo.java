package cz.homebrew.todolistbackend.api.v1.todoentry.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CreateUpdateTodoEntryReqTo {

//    TODO JAL: přidat validace a validační skupiny

    private Long id;
    private String title;
    private boolean finished;
}
