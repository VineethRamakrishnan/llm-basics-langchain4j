package com.vineeth.learnings;

import static dev.langchain4j.model.chat.Capability.RESPONSE_FORMAT_JSON_SCHEMA;

import com.vineeth.learnings.aiservices.ProfileAiService;
import com.vineeth.learnings.constants.OpenAIModelList;
import com.vineeth.learnings.model.Profile;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.output.OutputParsingException;

/**
 * This class demonstrates structured output implementation in LC4J.
 * Also, it implemented the AiServices concept
 * 
 * @author Vineeth
 *
 */
public class StructuredOutputCandidateProfiler {

	public static void main(String[] args) {
		
		String requestQuery = """
				I'm candidate applying for Tech lead position,
				I'm Vineeth from Chennai. I'm learnt male and having around 10 years of experience mainly in Backend development 
				involving Java, SpringBoot, Microservices tech.
				You can find my development work here https://github.com/VineethRamakrishnan
				""";
		
		//mapTheCandidateProfileInfoWithAiServices(requestQuery);
		mapTheCandidateErrorCase(requestQuery);

	}
	
	//Success scenario
	private static void mapTheCandidateProfileInfoWithAiServices(String requestQuery) {
		
		String apiKey=System.getenv("OPENAI_API_KEY");
		
		ChatModel model = OpenAiChatModel.builder()
					.apiKey(apiKey)
					.modelName(OpenAIModelList.MiniModel)
					.supportedCapabilities(RESPONSE_FORMAT_JSON_SCHEMA)			//To map the response back to java object
					.strictJsonSchema(true)
					.build();
		
		/**
		 * Usage of AI Services
		 * 
		 * Steps : Create AI serivce, Call them 
		 */
		ProfileAiService aiService = AiServices.create(ProfileAiService.class,model);
		Profile profile = aiService.extractProfile(requestQuery);
	
		System.out.println("Post extraction..");
		System.out.println("Extracted data : "+profile);
		
	}

	/**
	 * Errocase getting created manually by adding the max token limit
	 * @param requestQuery
	 */
	private static void mapTheCandidateErrorCase(String requestQuery) {
		
	String apiKey=System.getenv("OPENAI_API_KEY");
		
		ChatModel model = OpenAiChatModel.builder()
					.apiKey(apiKey)
					.modelName(OpenAIModelList.MiniModel)
					.supportedCapabilities(RESPONSE_FORMAT_JSON_SCHEMA)			//To map the response back to java object
					.strictJsonSchema(true)
					.maxCompletionTokens(20)									//To cause the parsing error
					.build();
		
		try {
			ProfileAiService aiService = AiServices.create(ProfileAiService.class,model);
			Profile profile = aiService.extractProfile(requestQuery);
		
			System.out.println("Post extraction..");
			System.out.println("Extracted data : "+profile);
		
		}catch (OutputParsingException ex) {									//framework specific exception
			
			System.out.println("Couldn't parse the response completelty, below error occurred.. \n" +ex.getMessage());
		}
		
	}
}
