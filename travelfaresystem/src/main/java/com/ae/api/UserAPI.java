package com.ae.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.beans.APIResponse;
import com.ae.beans.UserDTO;
import com.ae.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserAPI {

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseEntity<APIResponse> createUser(@ModelAttribute UserDTO user) {
		return ResponseEntity.ok().body(userService.createUser(user));
	}

	@GetMapping("/traveHistory/{cardId}")
	public ResponseEntity<APIResponse> travelHistory(@PathVariable(value = "cardId") Long cardId) {
		return ResponseEntity.ok().body(userService.travelHistory(cardId));
	}

}
