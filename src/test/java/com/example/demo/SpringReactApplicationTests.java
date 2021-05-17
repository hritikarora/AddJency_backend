package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Advertisement;
import com.example.demo.repository.AdRepository;

import java.lang.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.annotation.Rollback;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SpringReactApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdRepository adRepository;
	
	@Test
	public void testLoginValidationSuccess() throws URISyntaxException
	{
		User user = userRepository.findByEmailId("hritik.arora@iiitb.org");
		
		String pass = user.getPassword();
		
		Assert.assertEquals(true,pass.equals("arora97"));
		
	}
	@Test
	public void testLoginValidationFailure() throws URISyntaxException
	{
		User user = userRepository.findByEmailId("hritik.arora@iiitb.org");
		
		String pass = user.getPassword();
		
		Assert.assertEquals(false,pass.equals("wrongpass"));
		
	}
	@Test
	@Rollback(false)
	public void testCreateUserSuccess() {
		User user = new User();
		user.setCompanyName("");
		user.setEmailId("van@gmail.com");
		user.setFirstName("peter");
		user.setLastName("van");
		user.setPassword("peter_van");
		user.setUserType("Normal");
		
		User savedUser = userRepository.save(user);
		Long id = savedUser.getId();
		assertThat(id != (long)0).isTrue();
	}
	
	// @Test
	// @Rollback(false)
	// public void testCreateAdSuccess() {
	// 	Advertisement ad = new Advertisement();
	// 	ad.setAdDate("2021-5-18");
	// 	ad.setCompanyName("TOI");
	// 	ad.setDescription("testing purpose");
	// 	ad.setStatus("Pending");
	// 	ad.setUserId("3");
	// 	Advertisement savedAd = adRepository.save(ad);
	// 	Long id = savedAd.getId();
	// 	assertThat(id != (long)0).isTrue();
	// }

}
