package com.vineeth.learnings;

import java.util.HashMap;
import java.util.Map;

import com.vineeth.learnings.constants.OpenAIModelList;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * Prompt template has been used for implementing the dynamic queries like with placeholders.
 * 
 * @author Vineeth
 *
 */
public class PromptTemplateImplementation {

	public static void main(String[] args) {
		
		callPromptTemplateImplementation("Senior Engineer","Java");
		callPromptTemplateImplementation("Fresher","Angular");
		callPromptTemplateImplementation("Senior Architect","Devops");

	}
	
	
	private static void callPromptTemplateImplementation(String expLevel, String tech) {
		
		
		PromptTemplate template = PromptTemplate.from("You're hiring manager recruting for building critical systems."
				+ " Ask the top 5 questions from this {{field}} for this {{level}}");
		
		//Values should be in Object - Explain Why? TODO 
		Map<String,Object> inputMap = new HashMap<>();
		
		inputMap.put("field", tech);
		inputMap.put("level", expLevel);
		
		//Applying the variables to the template to make the prompt
		String finalPromptToLLM = template.apply(inputMap).text();
		
		System.out.println("\n\nPrompt : "+finalPromptToLLM);
		
		String apiKey=System.getenv("OPENAI_API_KEY");
		
		ChatModel model = OpenAiChatModel.builder()
					.apiKey(apiKey)
					.modelName(OpenAIModelList.MiniModel)
					.build();
		
		//Using the prompt generated through prompt for querying LLM
		String questions = model.chat(finalPromptToLLM);
		
		System.out.println("\n\n Questions :\n"+questions);
		
	}

}
