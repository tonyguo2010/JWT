package com.seakie.JWT;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/")
	public String index() {
		return "Welcome to all roles";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')") // Requires ROLE_USER
	public String userAccessible() {
		return "Accessible by USER role";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')") // Requires ROLE_ADMIN
	public String adminAccessible() {
		return "Accessible by ADMIN role";
	}

	@GetMapping("/userToken")
	public String userToken() {
		return JWTToken.generateUserToken();
	}
	
	@GetMapping("/adminToken")
	public String adminToken() {
		return JWTToken.generateAdminToken();
	}
	
}
