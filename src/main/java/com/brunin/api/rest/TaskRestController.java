package com.brunin.api.rest;

import com.brunin.api.dto.TaskDto;
import com.brunin.api.helper.DefaultResponseHelper;
import com.brunin.api.helper.TaskHelper;
import com.brunin.api.model.User;
import com.brunin.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public DefaultResponseHelper<TaskHelper> create(@RequestBody TaskHelper taskDto, @AuthenticationPrincipal User user) {
        return taskService.create(taskDto, user);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<TaskHelper>> getTasks(@PageableDefault(size=10, sort = {"deadline"}) Pageable pag) {
        return taskService.getTasks(pag);
    }
}
