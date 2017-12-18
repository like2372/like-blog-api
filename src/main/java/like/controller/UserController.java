package like.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@Api("UserController相关api")
public class UserController {
	
	@Autowired
	UserService userService;
	 
	 @ApiOperation("检查用户是否存在")
	 @RequestMapping(value="/checkUser",method=RequestMethod.GET)
	 public String checkUser(HttpServletRequest res){		 
		 
		 String userName=res.getParameter("userName");
		 
		 String password=res.getParameter("password");
		 
		 return userService.checkUser(userName,password);		 
	 }
	 
	 @ApiOperation("增加新用户")
	 @RequestMapping(value="/addUser",method=RequestMethod.POST)
	 public String addUser(HttpServletRequest res){
		 
		 String userName=res.getParameter("userName");
		 
		 String password=res.getParameter("password");
		 
		 return userService.addUser(userName,password);		
		 
	 }
	 
	 @ApiOperation("根据用户id删除用户")
	 @RequestMapping(value="/delUser",method=RequestMethod.POST)
	 public String delUser(HttpServletRequest res){
		 
		 String id=res.getParameter("id");		 
		 
		 return userService.delUser(id);		
		 
	 }
	 
}