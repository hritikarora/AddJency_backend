package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.Advertisement;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.AdRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdRepository adRepository;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}		
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		logger.info("New User Created");
		System.out.println(user.getFirstName());
		return userRepository.saveAndFlush(user);
	}
	
	@GetMapping("/users/{emailId}")
	public ResponseEntity<User> loginValidation(@PathVariable String emailId) {

		User user = userRepository.findByEmailId(emailId);
		
		String pass = user.getPassword();
		System.out.println(pass);
		logger.info("validation running");
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/adv")
	public Advertisement createAd(@RequestBody Advertisement ad)
	{
		logger.info("Ad posted");
		System.out.println(ad.getCompanyName());
		return adRepository.saveAndFlush(ad);
	}

	@GetMapping("/adv/{userId}")
	public List<Advertisement> getAllAds(@PathVariable String userId){
		long id = Long.parseLong(userId);
		logger.info("getting Ads");
		List<Advertisement> myList = adRepository.findByUserId(id);
		System.out.println(myList);
		return myList;
	}

	@GetMapping("/adv/{companyName}/{Status}")
	public List<Advertisement> getAllAdsByCompanyName(@PathVariable String companyName,@PathVariable String Status)
	{
		List<Advertisement> myList = adRepository.findByCompanyNameAndStatus(companyName,Status);
		System.out.println(myList);
		return myList;
	}
		
	@GetMapping("/companyname") 
	public List<String> findDistinctCompanyName()
	{ 
		return userRepository.findDistinctCompanyName(); 
	} 
}