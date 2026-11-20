package com.vineeth.learnings.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vineeth.learnings.constants.OpenAIModelList;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

@Configuration
public class AIConfiguration {

	@Value("${spring.boot.lc4j.apikey}")
	private String apiKey="";
	
	@Bean
	public ChatModel ChatModel() {
		
		return OpenAiChatModel.builder()
						.apiKey(apiKey)
						.modelName(OpenAIModelList.MiniModel)
						.build();
	}
	
}
