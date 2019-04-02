package org.apawaskar.vehiclelocator.controller;

import org.apawaskar.vehiclelocator.services.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

	@Autowired
	Producer producer;

	@RequestMapping(value = "/log", method = RequestMethod.POST, consumes = "application/json")
	public void submitLogs(@RequestBody String message) {
		LOGGER.debug("Message recieved: " + message);
		producer.sendMessageTo("/topic/logfeed", message);
	}
}
