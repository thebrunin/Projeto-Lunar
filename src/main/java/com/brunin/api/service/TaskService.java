package com.brunin.api.service;

import com.brunin.api.enums.TaskStatus;
import com.brunin.api.helper.DefaultResponseHelper;
import com.brunin.api.helper.TaskHelper;
import com.brunin.api.model.Task;
import com.brunin.api.model.User;
import com.brunin.api.repository.TaskRepository;
import com.brunin.api.repository.UserRepository;
import com.brunin.api.service.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;
    @Autowired
    UserRepository userRepository;

    public DefaultResponseHelper<TaskHelper> create(TaskHelper dto, User user) {
        if (dto.getResponsibleUser() != null && !dto.getResponsibleUser().isEmpty()) {
            User responsibleUser = userRepository.findById(dto.getResponsibleUser()).orElse(null);
            if (responsibleUser == null) {
                return new DefaultResponseHelper<>(false, "Responsible User not found by id: " + dto.getResponsibleUser());
            }
        }

        Task task = this.fromDto(dto);
        task.setUserId(user.getId());
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        TaskHelper helper = toDto(repository.save(task));
        return new DefaultResponseHelper<>(true, "Tarefa criada com sucesso", helper);
    }

    public ResponseEntity<Page<TaskHelper>> getTasks(Pageable pag) {
        var page = repository.findAll(pag).map(this::toDto);
        return ResponseEntity.ok(page);
    }

    private Task fromDto(TaskHelper dto) { return ModelMapperUtils.map(dto, Task.class); }

    private TaskHelper toDto(Task entity) { return ModelMapperUtils.map(entity, TaskHelper.class); }
}

