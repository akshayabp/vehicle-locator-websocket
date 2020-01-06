package org.apawaskar.vehiclelocator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	
	@Value("${broker.relay.hostname}")
	private String brokerRelayHostName;
	
	@Value("${broker.relay.port}")
	private int brokerRelayPort;

	@Value("${broker.relay.username}")
	private String brokerRelayUserName;

	@Value("${broker.relay.password}")
	private String brokerRelayPassword;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/log-socket").setAllowedOrigins("*").withSockJS();
		
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//registry.enableSimpleBroker("/queue/", "/topic/");
		
		
		
		StompBrokerRelayRegistration relayRegistration = registry.enableStompBrokerRelay("/queue/", "/topic/");

		relayRegistration.setRelayHost(brokerRelayHostName);
		relayRegistration.setRelayPort(brokerRelayPort);
		relayRegistration.setClientLogin(brokerRelayUserName);
		relayRegistration.setClientPasscode(brokerRelayPassword);

		registry.setApplicationDestinationPrefixes("/app");
	}

}
