package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.Captcha;
import vttp2023.batch3.ssf.frontcontroller.User;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@RestController
public class FrontController {

	@Autowired
	private AuthenticationService authenticationService;
	// TODO: Task 2, Task 3, Task 4, Task 6
	@GetMapping("/")
	public String getLandingPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("captcha", new Captcha());
		return "view0";
	}

	@PostMapping(path = "/login", 
		consumes = "application/x-www-form-urlencoded",
		produces = "text/html") 
	public String getLogin(@Valid User user, BindingResult result){
		if(result.hasErrors()) return "view0";
		return "view1";
	}

	@PostMapping(path = "/api/authenticate",
		consumes = "application/json",
		produces= "applcation/json")
	public void getAuthentication(@RequestBody User user) throws Exception{
		authenticationService.authenticate(user.getUsername(), user.getPassword());

	}
}
