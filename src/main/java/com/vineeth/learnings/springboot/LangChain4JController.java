package com.vineeth.learnings.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vineeth.learnings.aiservices.AiAssitant;

import dev.langchain4j.model.chat.ChatModel;

/**
 * Class to demonstrate Springboot application with Langchain4J
 * 	- Simple chat
 *  - AiService chat
 *  - AiService with system and user messages
 *  
 * @author Vineeth
 *
 */
@RestController
@RequestMapping(value="/api/lc4j")
public class LangChain4JController {

	private final ChatModel chatModel;
	
	private AiAssitant assistant;
	
	//Constructor Injection
	public LangChain4JController(ChatModel chatModel, AiAssitant assistant) {
		
		this.chatModel = chatModel;
		this.assistant = assistant;
		
	    System.out.println("LangChain4JController loaded");
	}

	@GetMapping("/hi")
	public String getHiMessage() {
		return "Hiii";
	}

	/**
	 * Implementation of simple prompting
	 * @param query
	 * @return
	 */
	@GetMapping("/chat")
	public String interactWithLLMThroughAPI(@RequestParam String query) {
		
		String response = chatModel.chat(query);
		//System.out.println("Response received from the LLM : "+response);
		return response;
	}
	
	
	/**
	 * Implementation of the AIServices concept
	 */
	@GetMapping("/aiservice/chat")
	public String chatWithLLMByAiService(@RequestParam String topic) {
		
		return assistant.chat(topic);
	}
	
	
	@GetMapping("/aiservice/explain")
	public String chatWithAdditionalArgsToLLMByAiService(@RequestParam String topic) {
		
		return assistant.explain(topic);
	}
}
