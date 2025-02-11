package cz.homebrew.todolistbackend.service.impl;

import cz.homebrew.todolistbackend.domain.TodoEntry;
import cz.homebrew.todolistbackend.repository.TodoEntryRepository;
import cz.homebrew.todolistbackend.service.TodoEntryService.CreateUpdateTodoEntryIn;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.MANDATORY)
@RequiredArgsConstructor
public class TodoEntryFactory {
    private final TodoEntryRepository todoEntryRepository;

    TodoEntry createTodoEntry(@NotNull final CreateUpdateTodoEntryIn in) {
        final TodoEntry todoEntry = new TodoEntry();
        modifyCommonValues(todoEntry, in);
        return todoEntry;
    }

    TodoEntry updateTodoEntry(@NotNull final CreateUpdateTodoEntryIn in,
                              @NotNull final Long todoEntryId) {
        // Start of implementation
        final TodoEntry todoEntry = todoEntryRepository.mustFindById(todoEntryId);
        modifyCommonValues(todoEntry, in);
        return todoEntry;
    }

    private void modifyCommonValues(@NotNull final TodoEntry todoEntry,
                                    @NotNull final CreateUpdateTodoEntryIn in) {
        todoEntry.setTitle(in.getTitle())
                 .setFinished(in.isFinished());
    }


}
