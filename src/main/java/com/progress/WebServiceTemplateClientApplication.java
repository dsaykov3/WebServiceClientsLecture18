package com.progress;


import com.progress.model.Contact;
import com.progress.model.GetContactRequest;
import com.progress.model.GetContactResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootApplication
public class WebServiceTemplateClientApplication implements CommandLineRunner {
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Autowired
	private RestTemplate restTemplate;
	public static void main(String[] args) {
		SpringApplication.run(WebServiceTemplateClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Soap client
		final GetContactRequest contactRequest = new GetContactRequest();
		contactRequest.setId("27");
		GetContactResponse response= (GetContactResponse)webServiceTemplate.marshalSendAndReceive(contactRequest);
		System.out.println(response.getContact().toString());

		//Rest client
		Contact restResponse = restTemplate.getForObject("http://localhost:8080/rest/find/31", Contact.class);
		System.out.println(restResponse.toString());
	}
}
