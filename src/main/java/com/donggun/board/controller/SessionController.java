package com.donggun.board.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/session")
@RestController
public class SessionController {

	@GetMapping(value = "/getSession")
	public String getSession() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		System.out.println("### " + securityContext + " ###");
		
		return "getSession";
	}
}
