package com.vineeth.learnings;

import java.util.List;
import java.util.Map;

import com.vineeth.learnings.constants.OpenAIModelList;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatResponseMetadata;

/**
 * Some useful information on ratelimiting headers
 * 
 * Refer : 
 * https://developers.openai.com/api/docs/guides/rate-limits#how-do-these-rate-limits-work
 * https://docs.langchain4j.dev/integrations/language-models/open-ai/
 * 
 * @author Vineeth
 *
 */
public class RatelimitImplementation {

	public static void main(String[] args) {
		
		explainRateLimitConcept();

	}
	
	private static void explainRateLimitConcept() {
		
		String apiKey=System.getenv("OPENAI_API_KEY");
		
		ChatModel model = OpenAiChatModel.builder()
					.apiKey(apiKey)
					.modelName(OpenAIModelList.MiniModel)
					.build();
		
		
		ChatResponse response = model.chat(UserMessage.from("Explain Navin kumar Reddy in 100 words"));
		OpenAiChatResponseMetadata metadata = (OpenAiChatResponseMetadata) response.metadata();
		Map<String, List<String>> headers = metadata.rawHttpResponse().headers();
		
		
		System.out.println("Metadata values :\n");
		System.out.println("Requests per minute : "+headers.get("x-ratelimit-limit-requests"));
		System.out.println("Requests left : "+headers.get("x-ratelimit-remaining-requests"));
		
		System.out.println("Total tokens left in the project "+headers.get("x-ratelimit-limit-project-tokens"));
		System.out.println("Tokens per minute : "+headers.get("x-ratelimit-limit-tokens"));
		System.out.println("Tokens left : "+headers.get("x-ratelimit-remaining-tokens"));
		
	}

}
