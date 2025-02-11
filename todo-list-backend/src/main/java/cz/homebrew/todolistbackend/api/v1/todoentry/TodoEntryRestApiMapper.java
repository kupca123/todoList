package cz.homebrew.todolistbackend.api.v1.todoentry;

import cz.homebrew.todolistbackend.api.v1.todoentry.dto.CreateUpdateTodoEntryReqTo;
import cz.homebrew.todolistbackend.api.v1.todoentry.dto.CreateUpdateTodoEntryResTo;
import cz.homebrew.todolistbackend.api.v1.todoentry.dto.ListTodoEntriesResTo;
import cz.homebrew.todolistbackend.service.TodoEntryService;
import cz.homebrew.todolistbackend.service.TodoEntryService.CreateUpdateTodoEntryIn;
import cz.homebrew.todolistbackend.service.TodoEntryService.CreateUpdateTodoEntryOut;
import cz.homebrew.todolistbackend.service.TodoEntryService.GetTodoEntriesOut;

public class TodoEntryRestApiMapper {

    static class ListTodoEntriesMapper {

        static ListTodoEntriesResTo mapToListTodoEntriesResTo(final GetTodoEntriesOut getTodoEntriesOut) {
            final ListTodoEntriesResTo listTodoEntriesResTo = new ListTodoEntriesResTo();
            if (getTodoEntriesOut == null) {
                return listTodoEntriesResTo;
            }

            listTodoEntriesResTo.setId(getTodoEntriesOut.getId())
                                .setTitle(getTodoEntriesOut.getTitle())
                                .setFinished(getTodoEntriesOut.isFinished());

            return listTodoEntriesResTo;
        }
    }

    static class CreateUpdateTodoEntryMapper {

        static CreateUpdateTodoEntryIn mapToCreateUpdateTodoEntryIn(final CreateUpdateTodoEntryReqTo reqTo) {
            final CreateUpdateTodoEntryIn todoEntryIn = new CreateUpdateTodoEntryIn();
            if (reqTo == null) {
                return todoEntryIn;
            }

            todoEntryIn.setId(reqTo.getId())
                       .setTitle(reqTo.getTitle())
                       .setFinished(reqTo.isFinished());

            return todoEntryIn;
        }

        static CreateUpdateTodoEntryResTo mapToCreateUpdateTodoEntryResTo(final CreateUpdateTodoEntryOut todoEntryOut) {
            final CreateUpdateTodoEntryResTo resTo = new CreateUpdateTodoEntryResTo();
            if (todoEntryOut == null) {
                return resTo;
            }

            resTo.setId(todoEntryOut.getId())
                 .setTitle(todoEntryOut.getTitle())
                 .setFinished(todoEntryOut.isFinished());

            return resTo;
        }
    }
}
