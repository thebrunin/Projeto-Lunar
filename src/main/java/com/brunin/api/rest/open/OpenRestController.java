package com.brunin.api.rest.open;

import com.brunin.api.helper.DefaultResponseHelper;
import com.brunin.api.model.User;
import com.brunin.api.repository.UserRepository;
import com.brunin.api.service.openAI.OpenAIGPTService;
import com.brunin.api.service.user.UserService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open")
public class OpenRestController {

    @Autowired
    OpenAIGPTService openAiService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/status")
    public String getStatus() {
        return "Servidor online";
    }

    @PostMapping("/gpt/ask")
    public DefaultResponseHelper<ChatMessage> getStatus(@RequestBody String prompt) {
        return new DefaultResponseHelper<>(true, "Executado com sucesso", openAiService.ask(prompt));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getUsers(@PageableDefault(size=10, sort = {"name"}) Pageable pag) {
        var page = userRepository.findAll(pag);
        return ResponseEntity.ok(page);
    }
}
