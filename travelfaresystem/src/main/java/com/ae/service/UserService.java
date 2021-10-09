package com.ae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ae.beans.APIResponse;
import com.ae.beans.UserDTO;

@Service
@Transactional
public interface UserService {

	APIResponse createUser(UserDTO dto);

	APIResponse travelHistory(Long cardId);

}
