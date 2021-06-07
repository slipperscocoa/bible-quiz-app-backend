package com.biblequizappbackend.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class LoginController {

	  @Autowired
	  private MySQLUserDetailsService userService;

	  @PostMapping("/register")
	  public void register(@RequestBody Login newUser) {
	    userService.Save(newUser);
	  }
}
