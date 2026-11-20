package com.vineeth.learnings;

import java.time.Duration;

import com.vineeth.learnings.constants.OpenAIModelList;

import dev.langchain4j.exception.NonRetriableException;
import dev.langchain4j.exception.RetriableException;
import dev.langchain4j.exception.TimeoutException;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * Timeout and retry logic implementation
 * 
 * Errorcode Open AI doc - https://developers.openai.com/api/docs/guides/error-codes
 * 
 * @author Vineeth
 *
 */
public class TimeoutRetriesImplementation {

	public static void main(String[] args) {
		
		String apiKey=System.getenv("OPENAI_API_KEY");
		
		//Timeout concept
		ChatModel model = modelInitialization(apiKey, OpenAIModelList.MiniModel, Duration.ofMillis(5000), 3);
		explainTimeoutConcept(model);
		
		//Invalid API-key
		model = modelInitialization(apiKey+"fer", OpenAIModelList.MiniModel, Duration.ofMillis(5000), 3);
		explainRetryConcept(model);
		
		//Invalid model name
		model = modelInitialization(apiKey, "Mini", Duration.ofMillis(5000), 3);
		explainRetryConcept(model);
		
		//Lesser timeout
		model = modelInitialization(apiKey, OpenAIModelList.MiniModel, Duration.ofMillis(1000), 3);
		explainRetryConcept(model);

	}
	
	/**
	 * Explaining the timeout which is manually set
	 */
	private static void explainTimeoutConcept(ChatModel model) {
		
		try {
			
			String result = model.chat("Explain me Docker desktop in 600 workds");
			System.out.println(result);
		}
		catch(TimeoutException ex) {			//timeoutexception in lc4j
			
			System.out.println("Timeout exception occurred.. \n"+ex.getMessage());
		}
		
	}
	
	
	/**
	 * It would retry only for the retriable exceptions  - Timeout exception / Ratelimit exception / ISE
	 * NOT for the non-retriable exceptions - Authentication exception / Invalid request exception / ModelNotfound exception
	 * @param model
	 */
	private static void explainRetryConcept(ChatModel model) {
		
		try {
			
			String result = model.chat("Explain me Docker desktop in 600 workds");
			System.out.println(result);
			System.out.println("\n\n ==== Positive case ==== ");
		}
		catch(RetriableException ex) {			//timeoutexception in lc4j
			
			System.out.println("\nRetriable exception occurred.. And the exception is "+ex.getClass().getSimpleName());
		}
		catch(NonRetriableException ex) {
			
			System.out.println("\nNon-Retriable exception occurred.. And the exception is "+ex.getClass().getSimpleName());
		}
	}

	
	private static ChatModel modelInitialization(String apiKey, String modelNameValue, Duration timeoutValue, int retryCount) {
				
		
		ChatModel model = OpenAiChatModel.builder()
					.apiKey(apiKey)
					.modelName(modelNameValue)
					.timeout(timeoutValue)			//timeout configuration which is manually set
					.maxRetries(retryCount)
					.build();
		
		return model;
	}
}
