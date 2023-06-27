package com.adrianosbotelho.chatgptbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.adrianosbotelho.chatgptbot.dto.ChatGPTRequest;
import com.adrianosbotelho.chatgptbot.dto.ChatGPTResponse;

@RestController
@RequestMapping("/bot")
public class CustomBotController {
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.api.url}")
	private String apiUrl;
	
	@Autowired
	private RestTemplate template;
	
	
	@GetMapping("/chat")
	public String chat(@RequestParam("prompt") String prompt) {
		var request = new ChatGPTRequest(model, prompt);
		ChatGPTResponse chatGPTResponse = template.postForObject(apiUrl, request, ChatGPTResponse.class);
		return chatGPTResponse.getChoices().get(0).getMessage().getContent();
	}
	
}
