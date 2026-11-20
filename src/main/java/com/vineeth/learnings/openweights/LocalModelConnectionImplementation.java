package com.vineeth.learnings.openweights;

import java.time.Duration;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.ollama.OllamaChatModel;

/**
 * It demonstrates the implementation of the connectivity with locally running models 
 * 
 * 	- Without any apikey
 *  - much faster response
 *  
 *  NOTE: Due to local machine limitation, simple prompts given. we can change that later.
 *  
 * @author Vineeth
 *
 */
public class LocalModelConnectionImplementation {

	public static void main(String[] args) {
		
		chatWithLocalModel();

	}

	private static void chatWithLocalModel() {
		
		ChatModel model = OllamaChatModel.builder()
								.baseUrl("http://localhost:11434")				//where the ollama is running
								.modelName("mistral:latest")
								.timeout(Duration.ofMinutes(2))
								.build();
		
		String response = model.chat("Hi, tell me your model name");
		System.out.println(response);
		
		System.out.println("===================");
		
		long start = System.currentTimeMillis();
		
        ChatResponse chatResponse = model.chat(
                UserMessage.from("Give me three best pick-up lines which would be unique"));

        System.out.println(chatResponse.aiMessage().text());
        System.out.println("(took " + (System.currentTimeMillis() - start) + " ms, "
                + "input tokens: " + chatResponse.tokenUsage().inputTokenCount() + ", "
                + "output tokens: " + chatResponse.tokenUsage().outputTokenCount() + ")");

	}
}
