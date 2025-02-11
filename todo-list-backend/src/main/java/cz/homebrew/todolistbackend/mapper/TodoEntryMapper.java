package cz.homebrew.todolistbackend.mapper;

import cz.homebrew.todolistbackend.domain.TodoEntry;
import cz.homebrew.todolistbackend.service.TodoEntryService.CreateUpdateTodoEntryOut;
import cz.homebrew.todolistbackend.service.TodoEntryService.GetTodoEntriesOut;

public class TodoEntryMapper {

    public static class GetTodoEntriesMapper {

        public static GetTodoEntriesOut mapToGetTodoEntriesOut(final TodoEntry todoEntry) {
            final GetTodoEntriesOut out = new GetTodoEntriesOut();
            if (todoEntry == null) {
                return out;
            }
            out.setId(todoEntry.getId())
               .setTitle(todoEntry.getTitle())
               .setFinished(todoEntry.isFinished());
            return out;
        }
    }
    public static class CreateUpdateTodoEntryMapper {
        public static CreateUpdateTodoEntryOut mapToCreateUpdateTodoEntryOut(final TodoEntry todoEntry) {
            final CreateUpdateTodoEntryOut out = new CreateUpdateTodoEntryOut();
            if (todoEntry == null) {
                return out;
            }
            out.setId(todoEntry.getId())
               .setTitle(todoEntry.getTitle())
               .setFinished(todoEntry.isFinished());
            return out;
        }
    }


}
