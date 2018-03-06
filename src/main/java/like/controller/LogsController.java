package like.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

import like.service.LogsService;
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
@RequestMapping("/logsService")
@Api("logsService相关api")
public class LogsController {
	
	 @Autowired
	 LogsService logsService;
	 
	 @ApiOperation("根据开始数量和结束数量查询日志")
	 @ApiImplicitParams({
			@ApiImplicitParam(paramType="query",name="start",dataType="String",value="开始的数量",defaultValue="0"),
			@ApiImplicitParam(paramType="query",name="end",dataType="String",value="结束的的数量",defaultValue="6")
	 })
	 @RequestMapping(value="/getLogs",method=RequestMethod.GET)
	 public String getLogs(HttpServletRequest res){		 
		 
		 String startNumber=res.getParameter("startNumber");
		 
		 String endNumber=res.getParameter("endNumber");
		 
		 String ip=res.getParameter("ip");
		 
		 String path=res.getParameter("path");
		 
		 String type=res.getParameter("type");
		 
		 return logsService.getLogs(startNumber, endNumber,ip,path,type);
	 }
	 
	 
	 	 
}