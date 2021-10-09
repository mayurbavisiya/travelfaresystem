package com.ae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ae.beans.APIResponse;
import com.ae.beans.SwipeInReqDTO;
import com.ae.beans.SwipeOutReqDTO;
import com.ae.exception.ValidationException;

@Service
@Transactional
public interface TravelfareService{

	APIResponse checkBalance(Long cardiId) throws ValidationException;
	
	APIResponse swipeIn(SwipeInReqDTO dto) throws ValidationException;

	APIResponse swipeOut(SwipeOutReqDTO dto) throws ValidationException;

}
