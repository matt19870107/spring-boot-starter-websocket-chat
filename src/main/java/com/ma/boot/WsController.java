package com.ma.boot;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) throws InterruptedException {
		if(principal.getName().equals("matt")) {
			messagingTemplate.convertAndSendToUser("matt1", "/queue", principal.getName() + "-send: " + msg);
		}else {
			messagingTemplate.convertAndSendToUser("matt", "/queue", principal.getName() + "-send: " + msg);
		}
	}
}
