package com.ae.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ae.beans.APIResponse;
import com.ae.beans.SwipeInReqDTO;
import com.ae.beans.SwipeOutReqDTO;
import com.ae.exception.ValidationException;
import com.ae.service.TravelfareService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BuisnessAPI {

	@Autowired
	TravelfareService travelfareService;

	@GetMapping("/balance/{cardId}")
	public ResponseEntity<APIResponse> checkBalance(@PathVariable(value = "cardId") Long cardId) throws ValidationException {
		return ResponseEntity.ok().body(travelfareService.checkBalance(cardId));
	}

	@PostMapping("/swipe/in")
	public ResponseEntity<APIResponse> swipeIn(@RequestBody SwipeInReqDTO dto) throws ValidationException {
		return ResponseEntity.ok().body(travelfareService.swipeIn(dto));
	}

	@PostMapping("/swipe/out")
	public ResponseEntity<APIResponse> swipeOut(@RequestBody SwipeOutReqDTO dto) throws ValidationException {
		return ResponseEntity.ok().body(travelfareService.swipeOut(dto));
	}
}
