package com.mohit.springbootawssqs.config;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class SQSAwsConfig {
	
	@Bean
	public QueueMessagingTemplate messagingTemplate() {
		return new QueueMessagingTemplate(amazonSQSAsync());
	}

	@Bean
	@Primary
	public AmazonSQSAsync amazonSQSAsync() {
		
		return AmazonSQSAsyncClientBuilder.standard().withRegion("AWS_REGION")
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("ACCESS_KEY", "SECRET_KEY"))).build();
	}

}
