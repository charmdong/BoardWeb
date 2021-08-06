package com.donggun.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping(value = "/system/login")
	public void login() { /* empty */ }
	
	@GetMapping(value = "/system/accessDenied")
	public void accessDenied() { /* empty */ }
	
	@GetMapping(value = "/system/logout")
	public void logout() { /* empty */ }
	
	@GetMapping(value = "/admin/adminPage")
	public void admin() { /* empty */ }
}
