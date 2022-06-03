package com.sean.taller.frontcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sean.taller.user.UserEntity;

@Controller
@RequestMapping("")
public class LoginController {

	    @GetMapping("/login")
	    public String login(UserEntity userEntity){
	        return "login";
	        
	    }

	    @GetMapping("/logout")
	    public String getLogout(){
	        return "logout";
	        
	    }
	    
	    @PostMapping("/logout")
	    public String postLogout(){
	        return "logout";
	        
	    }

	    @GetMapping("/access-denied")
	    public String accessDenied(){
	        return "access-denied";
	        
	    }
}
