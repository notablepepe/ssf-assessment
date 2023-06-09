package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.ssf.frontcontroller.User;
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;

@Service
public class AuthenticationService {
	//private static final double LOCK_TIME_DURATION = 0.5 * 60 * 60 * 1000; // 30mins

	@Autowired
	private AuthenticationRepository authenticationRepository;
	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {
		String url = UriComponentsBuilder
						.fromUriString("https://authservice-production-e8b2.up.railway.app")
						.queryParam("username", username)
						.queryParam("password", password)
						.toUriString();

		JsonObject json = Json.createObjectBuilder()
						.add("username", username)
						.add("password", password).build();

		authenticationRepository.save(new User(username, ))
						
		RequestEntity<String> req = RequestEntity
									.post(url)
									.contentType(MediaType.APPLICATION_JSON)
									.header("Accept", MediaType.APPLICATION_JSON.toString())
									.body(json.toString(), String.class);
		
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = template.exchange(req, String.class);


	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		authenticationRepository.lock(authenticationRepository.get(username).get());

	
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return authenticationRepository.get(username).get().isLocked();
	}
}
