package like.aop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import like.entity.LogEntity;
import like.mapper.LogsMapper;
import like.util.DicConstants;
import like.util.HttpHelpUtil;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 切面管理
 * @author like
 *
 */
@Aspect
@Component
public class WebControllerAop {
		
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LogsMapper logsMapper;
	@Pointcut("execution(* like.controller..*(..))")
	public void exectueService(){
		
	}
	
	/**
	 * 前置通知
	 */
	@Before("exectueService()")
	public void doBeforeAdvice(JoinPoint joinPoint){
						 
		 ServletRequestAttributes attributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

         HttpServletRequest request = (HttpServletRequest) attributes.getRequest();
         
         try{
        	 
        	LogEntity log=new LogEntity(); 
        	
        	UUID uuid=UUID.randomUUID();
        	
        	log.setId(uuid.toString());
        	
        	String ip=HttpHelpUtil.getIpAddress(request);
 			
        	String realIp=request.getHeader("X-real-ip");
        	
        	log.setIp(realIp);
        	
      		Date date =new Date();
      		
      		SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
      		
      		String newDate=sf.format(date);
      		
      		log.setTime(newDate);
      		
      		log.setPath(joinPoint.getSignature().getName());
      		     		
      		Enumeration<String> enumer= request.getParameterNames();
      		      	     		     		      		   		
      		while(enumer.hasMoreElements()){
      			
      			String value=(String)enumer.nextElement();
      			
      			log.setContent("参数为"+value+"值为"+request.getParameter(value));
      			
      		}
      		
      		if(log.getPath()!="getLogs"){
      			
      			logsMapper.insertLogs(log);
      			
      		}
      		
      		
      		logger.info("用户的ip为"+log.getIp());
      		
      		logger.info("当前时间为"+log.getTime());		
      		
      		logger.info("请求的方法为"+log.getPath());
      		     			
      		logger.info(log.getContent());
      		
         }catch(Exception e){
        	 
        	 logger.info("前置通知发生错误"+e.getMessage());
        	 
         }
                  				
		
		
	}
	
	/**
	 * 后置通知
	 * @param joinPoint
	 */
	@AfterReturning("exectueService()")
	public void doAfterReturning(JoinPoint joinPoint){
		
		logger.info("执行"+joinPoint.getSignature().getName()+"方法完成");
		
	}
	
	 /**
     * 解析path
     */
    private Map<String,String> getContentByPath(String path){
    		
    	if(path==null||path=="") {
			return null;
		}
    	
    	String content="";
    	
    	String type="";
    	
    	Map<String,String> resultMap=new HashMap<String,String>();
    	
    	String[] pathStr=path.split("/");
    	
    	content=DicConstants.contentMap.get(pathStr[2]);
    	
    	type=DicConstants.typeMap.get(pathStr[2]);
    	
    	resultMap.put("content", content);
    	
    	resultMap.put("type", type);
    	
    	return resultMap;
    	
    }
	
}
