package vn.iotstar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}

	@GetMapping("/user/profile")
	public String profilePage() {
		return "profile";
	}
}
