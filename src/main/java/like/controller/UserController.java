package like.controller;

import javax.servlet.http.HttpServletRequest;

import like.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口类
 * @author like
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/userService")
public class UserController {
	
	@Autowired
	UserService userService;
	
	 @RequestMapping("/")  
	   public String home() {  
	        return "Hello World!";  
	    }  
	 
	 @RequestMapping("/checkUser")
	 public String checkUser(HttpServletRequest res){		 
		 
		 String userName=res.getParameter("userName");
		 
		 String password=res.getParameter("password");
		 
		 return userService.checkUser(userName,password);		 
	 }
	 
	 @RequestMapping(value="/addUser",method=RequestMethod.POST)
	 public String addUser(HttpServletRequest res){
		 
		 String userName=res.getParameter("userName");
		 
		 String password=res.getParameter("password");
		 
		 return userService.addUser(userName,password);		
		 
	 }
	 
	 @RequestMapping(value="/delUser",method=RequestMethod.POST)
	 public String delUser(HttpServletRequest res){
		 
		 String id=res.getParameter("id");		 
		 
		 return userService.delUser(id);		
		 
	 }
	 
}