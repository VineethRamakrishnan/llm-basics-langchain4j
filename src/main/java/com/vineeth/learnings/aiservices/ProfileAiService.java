package com.vineeth.learnings.aiservices;

import com.vineeth.learnings.model.Profile;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

/**
 * AIServices are used to chat with the LLM without deliberately writing boiler-plate code
 * 
 * Advantages
 * 		- Abstraction of low level APIs
 * 		- Return type can be specified
 * 		- Similar syntax of Java Interface - Class implementation model
 * 
 * @author Vineeth
 *
 */
public interface ProfileAiService {

	@SystemMessage("Read the user message and return the details as JSON")
	Profile extractProfile(@UserMessage String inputString);
}
