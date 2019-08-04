package com.materiel.gestion.apigestion.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.materiel.gestion.apigestion.config.JwtTokenUtil;
import com.materiel.gestion.apigestion.model.dto.ErrorDto;
import com.materiel.gestion.apigestion.model.dto.JwtResponse;
import com.materiel.gestion.apigestion.model.entite.User;
import com.materiel.gestion.apigestion.service.IUserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController implements AuthenticationEntryPoint {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody User user) throws Exception {
		authenticate(user.getUsername(), user.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(userService.save(user));
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		int httpCode = ApiExceptionHandler.findHttpStatus(authException).value();
		response.setStatus(httpCode);
		String json = new ObjectMapper().writeValueAsString(new ErrorDto(httpCode, authException.getMessage()));
		response.getWriter().write(json);
		response.flushBuffer();
	}
}
