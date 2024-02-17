package com.brunin.api.rest.open;

import com.brunin.api.helper.DefaultResponseHelper;
import com.brunin.api.service.openAI.OpenAIGPTService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/open")
public class OpenRestController {

    @Autowired
    OpenAIGPTService openAiService;

    @GetMapping("/status")
    public String getStatus() {
        return "Servidor online";
    }
    @PostMapping("/gpt/ask")
    public DefaultResponseHelper<ChatMessage> getStatus(@RequestBody String prompt) {
        return new DefaultResponseHelper<>(true, "Executado com sucesso", openAiService.ask(prompt));
    }
}
