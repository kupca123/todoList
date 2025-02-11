package cz.homebrew.todolistbackend.api.v1.todoentry;

import cz.homebrew.todolistbackend.api.v1.todoentry.TodoEntryRestApiMapper.CreateUpdateTodoEntryMapper;
import cz.homebrew.todolistbackend.api.v1.todoentry.TodoEntryRestApiMapper.ListTodoEntriesMapper;
import cz.homebrew.todolistbackend.api.v1.todoentry.dto.CreateUpdateTodoEntryResTo;
import cz.homebrew.todolistbackend.api.v1.todoentry.dto.ListTodoEntriesResTo;
import cz.homebrew.todolistbackend.api.v1.todoentry.dto.CreateUpdateTodoEntryReqTo;
import cz.homebrew.todolistbackend.service.TodoEntryService;
import cz.homebrew.todolistbackend.service.TodoEntryService.CreateUpdateTodoEntryIn;
import cz.homebrew.todolistbackend.service.TodoEntryService.GetTodoEntriesOut;
import cz.homebrew.todolistbackend.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todoEntry")
@RequiredArgsConstructor
public class TodoEntryResource {

    private final TodoEntryService todoEntryService;

    @GetMapping
    public ResponseEntity<?> listTodoEntries() {
        final List<GetTodoEntriesOut> getTodoEntriesOutList = todoEntryService.getTodoEntries();
        final List<ListTodoEntriesResTo> resToList
                = MapperUtils.mapList(getTodoEntriesOutList, ListTodoEntriesMapper::mapToListTodoEntriesResTo);
        return new ResponseEntity<>(resToList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTodoEntry(@RequestBody final CreateUpdateTodoEntryReqTo reqTo) {
        final CreateUpdateTodoEntryIn in = CreateUpdateTodoEntryMapper.mapToCreateUpdateTodoEntryIn(reqTo);
        todoEntryService.createUpdateTodoEntry(in);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTodoEntry(@RequestBody final CreateUpdateTodoEntryReqTo reqTo) {
        final CreateUpdateTodoEntryIn in = CreateUpdateTodoEntryMapper.mapToCreateUpdateTodoEntryIn(reqTo);
        final TodoEntryService.CreateUpdateTodoEntryOut todoEntryOut = todoEntryService.createUpdateTodoEntry(in);
        final CreateUpdateTodoEntryResTo resTo = CreateUpdateTodoEntryMapper.mapToCreateUpdateTodoEntryResTo(todoEntryOut);
        return new ResponseEntity<>(resTo, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{todoEntryId}")
    public ResponseEntity<?> deleteTodoEntry(@PathVariable final Long todoEntryId) {
        todoEntryService.deleteTodoEntry(todoEntryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
