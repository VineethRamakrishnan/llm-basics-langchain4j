package com.vineeth.learnings;

import com.vineeth.learnings.aiservices.ProfileAiService;
import com.vineeth.learnings.constants.OpenAIModelList;
import com.vineeth.learnings.model.Profile;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import static dev.langchain4j.model.chat.Capability.RESPONSE_FORMAT_JSON_SCHEMA;

/**
 * This class demonstrates structured output implementation in LC4J.
 * Also, it implemented the AiServices concept
 * 
 * @author Vineeth
 *
 */
public class StructuredOutputCandidateProfiler {

	public static void main(String[] args) {
		
		mapTheCandidateProfileInfoWithAiServices();

	}
	
	private static void mapTheCandidateProfileInfoWithAiServices() {
		
		String apiKey=System.getenv("OPENAI_API_KEY");
		
		ChatModel model = OpenAiChatModel.builder()
					.apiKey(apiKey)
					.modelName(OpenAIModelList.MiniModel)
					.supportedCapabilities(RESPONSE_FORMAT_JSON_SCHEMA)			//To map the response back to java object
					.strictJsonSchema(true)
					.build();
		
		String inputString = """
				I'm candidate applying for Tech lead position,
				I'm Vineeth from Chennai. I'm learnt male and having around 10 years of experience mainly in Backend development 
				involving Java, SpringBoot, Microservices tech.
				You can find my development work here https://github.com/VineethRamakrishnan
				""";

		/**
		 * Usage of AI Services
		 * 
		 * Steps : Create AI serivce, Call them 
		 */
		ProfileAiService aiService = AiServices.create(ProfileAiService.class,model);
		Profile profile = aiService.extractProfile(inputString);
	
		System.out.println("Post extraction..");
		System.out.println("Extracted data : "+profile);
		
	}

}
