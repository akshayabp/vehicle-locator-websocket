package org.apawaskar.vehiclelocator.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Autowired
    private SimpMessagingTemplate template;

    public void sendMessageTo(String topic, String message) {
    	//this.template.convertAndSend( topic, message);  
    	try {
			template.send(topic, MessageBuilder.withPayload(message.getBytes("UTF-8")).build());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	
    }
}
