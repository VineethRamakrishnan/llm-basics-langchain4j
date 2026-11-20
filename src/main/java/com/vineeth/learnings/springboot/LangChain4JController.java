package com.vineeth.learnings.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.langchain4j.model.chat.ChatModel;

/**
 * Class to demonstrate Springboot application with Langchain4J
 * @author Dell
 *
 */
@RestController
@RequestMapping(value="/api/lc4j")
public class LangChain4JController {

	private final ChatModel chatModel;
	
	//Constructor Injection
	public LangChain4JController(ChatModel chatModel) {
		this.chatModel = chatModel;
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
	
}
