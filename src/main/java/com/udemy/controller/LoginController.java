package com.udemy.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.constant.ViewConstant;
import com.udemy.model.UserCredential;

@Controller
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);
	
	@GetMapping("/login")
	public String showLoginForm(
			Model model,
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout){
		log.info("METHOD: showLoginForm() -- PARAMS: error = " + error + ", logout: " + logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		model.addAttribute("userCredentials", new UserCredential());
		return ViewConstant.LOGIN;
	}
	
	@GetMapping({"/loginsuccess", "/"})
	public String loginCheck(){
		log.info("METHOD: loginCheck()");
			return "redirect:/contacts/showcontacts";

	}
}
