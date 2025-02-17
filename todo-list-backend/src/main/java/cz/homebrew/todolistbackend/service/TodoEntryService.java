package cz.homebrew.todolistbackend.service;

import cz.homebrew.todolistbackend.wrapper.PageableListOut;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoEntryService {

    PageableListOut<GetTodoEntriesOut> getTodoEntries(ListTodoEntriesFilterIn filterIn);

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
    class ListTodoEntriesFilterIn {
        private Pageable pageable;
    }

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
