package cz.homebrew.todolistbackend.service.impl;

import cz.homebrew.todolistbackend.domain.TodoEntry;
import cz.homebrew.todolistbackend.mapper.TodoEntryMapper;
import cz.homebrew.todolistbackend.mapper.TodoEntryMapper.CreateUpdateTodoEntryMapper;
import cz.homebrew.todolistbackend.mapper.TodoEntryMapper.GetTodoEntriesMapper;
import cz.homebrew.todolistbackend.repository.TodoEntryRepository;
import cz.homebrew.todolistbackend.service.TodoEntryService;
import cz.homebrew.todolistbackend.utils.MapperUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoEntryServiceImpl implements TodoEntryService {

    private final TodoEntryRepository todoEntryRepository;
    private final TodoEntryFactory todoEntryFactory;

    @Transactional(readOnly = true)
    @Override
    public List<GetTodoEntriesOut> getTodoEntries() {
        final List<TodoEntry> todoEntryList = todoEntryRepository.findAll();
        return MapperUtils.mapList(todoEntryList, GetTodoEntriesMapper::mapToGetTodoEntriesOut);
    }

    @Override
    public CreateUpdateTodoEntryOut createUpdateTodoEntry(@NotNull final CreateUpdateTodoEntryIn in) {
        final TodoEntry todoEntry;
        if (in.getId() == null) {
            todoEntry = todoEntryFactory.createTodoEntry(in);

        } else {
            todoEntry = todoEntryFactory.updateTodoEntry(in, in.getId());
        }
        todoEntryRepository.save(todoEntry);

        return CreateUpdateTodoEntryMapper.mapToCreateUpdateTodoEntryOut(todoEntry);
    }

    @Override
    public Long deleteTodoEntry(@NotNull final Long id) {
        todoEntryRepository.deleteById(id);
        return id;
    }
}
