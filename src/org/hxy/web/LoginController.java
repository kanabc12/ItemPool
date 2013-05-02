package org.hxy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/login.html")
	public String showLoginPage(){
		return "login";
	}
}
