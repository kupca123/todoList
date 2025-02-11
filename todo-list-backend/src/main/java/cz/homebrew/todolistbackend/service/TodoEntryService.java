package cz.homebrew.todolistbackend.service;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

public interface TodoEntryService {

    List<GetTodoEntriesOut> getTodoEntries();

    CreateUpdateTodoEntryOut createUpdateTodoEntry(@NotNull final CreateUpdateTodoEntryIn in);

    Long deleteTodoEntry(@NotNull final Long id);


    //=====================================//
    //                                     //
    //          DTO In Objects             //
    //                                     //
    //=====================================//

    @Getter
    @Setter
    @Accessors(chain = true)
    class CreateUpdateTodoEntryIn {
        private Long id;
        private String title;
        private boolean finished;
    }


    //=====================================//
    //                                     //
    //          DTO Out Objects            //
    //                                     //
    //=====================================//

    @Getter
    @Setter
    @Accessors(chain = true)
    class GetTodoEntriesOut {
        private Long id;
        private String title;
        private boolean finished;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    class CreateUpdateTodoEntryOut {
        private Long id;
        private String title;
        private boolean finished;
    }
}
