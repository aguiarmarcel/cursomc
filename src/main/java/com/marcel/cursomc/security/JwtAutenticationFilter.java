package com.marcel.cursomc.security;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcel.cursomc.dto.CredenciaisDTO;

public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager AuthenticationManager;
	
	private JwtUtil jwtUtil;
	
	public JwtAutenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.AuthenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, 
												HttpServletResponse res) {
		
		try {
			CredenciaisDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), 
																									creds.getSenha(),
																									new ArrayList<>());
			Authentication auth = AuthenticationManager.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
		
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
										   HttpServletResponse res,
										   FilterChain chain,
										   Authentication auth) throws IOException, ServerException {
		
	String username = ((UserSS) auth.getPrincipal()).getUsername();
	String token = jwtUtil.generateToken(username);
	res.addHeader("Athorization", "Beaner " + token);
	
	}
}
