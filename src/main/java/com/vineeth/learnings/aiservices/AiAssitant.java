package com.vineeth.learnings.aiservices;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

/**
 * Its similar to the normal application but usage would be easier. Please refer LangChain4JController endpoints
 * @author Vineeth
 *
 */
@AiService
public interface AiAssitant {

	public String chat(String query);
	
	
	@SystemMessage("""
            You are an expert Java instructor.
            Explain everything in simple language.
            Give short answers with examples.
            """)
    @UserMessage("Explain {{topic}}")
	public String explain(String topic);
	
}
