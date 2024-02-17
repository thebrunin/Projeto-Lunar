package com.brunin.api.service.openAI;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatFunction;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OpenAIGPTService {
    @Value("${openai.api-key}")
    private String API_KEY;

//    public List<CompletionChoice> ask(String prompt) {
//        OpenAiService service = new OpenAiService(this.API_KEY);
//        CompletionRequest request = CompletionRequest.builder()
//                //.model("gpt-3.5-turbo-0125")
//                .model("babbage-002")
//                .prompt(prompt)
//                .maxTokens(100)
//                .build();
//        try{
//            List<CompletionChoice> choices = service.createCompletion(request).getChoices();
//            return choices;
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    public ChatMessage ask(String prompt) {
        OpenAiService service = new OpenAiService(this.API_KEY);

        try{
            List<ChatMessage> messages = new ArrayList<>();
            ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
            messages.add(userMessage);
            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model("gpt-3.5-turbo-0613")
                    .messages(messages)
                    //.functions(functionExecutor.getFunctions())
                    //.functionCall(new ChatCompletionRequestFunctionCall("auto"))
                    .maxTokens(256)
                    .build();

            return service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
