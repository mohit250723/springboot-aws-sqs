package com.mohit.springbootawssqs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SQSController {
	Logger logger= LoggerFactory.getLogger(SQSController.class);

	@Autowired
	private QueueMessagingTemplate messagingTemplate;
	
	@PostMapping("/publish")
	public String sendMessageToQueue(@RequestParam String pubMessage) {
		messagingTemplate.send("SQS-QUEUE-URL",MessageBuilder.withPayload(pubMessage).build());
		return "Message Added to Queue...";
	}
	
	@SqsListener("javahandson-Queue")
	public void readQueueMessage(String mess) {
		logger.info("Message from SQS {}",mess);	
	}
	
}
