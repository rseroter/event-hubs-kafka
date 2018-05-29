package seroter.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Source.class)
@RestController
@SpringBootApplication
public class EventHubsKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventHubsKafkaApplication.class, args);
	}
	
	@Autowired
	Source mySource;

	@RequestMapping(method=RequestMethod.POST, path="/")
	public String PublishMessage(@RequestBody String company) {

		mySource.output().send(MessageBuilder.withPayload(company).build());
		
		return "success";
	}
}
