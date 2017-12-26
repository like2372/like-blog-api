package like.service.impl;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import like.entity.UserEntity;
import like.mapper.UserMapper;
import like.service.UserService;
import like.util.Constants;
import like.util.JWTUtil;
import like.util.MD5Util;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 用户接口类
 * @author like
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 验证用户是否存在
	 * @param userName
	 * @param password
	 * @return 是否成功json字符串
	 */
	public String checkUser(String userName,String password) {
		
		JSONObject resultJson=new JSONObject();
		
		boolean result=false;
		
		String token="";
		
		try{			
			
			String mdPasswordString=MD5Util.Md5Encrypt(password);
			
			UserEntity user=new UserEntity();
			
			user.setUserName(userName);
			
			user.setPassword(mdPasswordString);
			
			user=userMapper.checkUser(user);
			
			if(user!=null){
										
				String id=user.getId();
					
				result=true;
									
				String subject=JWTUtil.generalSubject(user);
					
				token=JWTUtil.createToken(Constants.JWT_ID, subject, Constants.JWT_TTL);
				
			}
			
		}catch(Exception e){
			
			result=false;
			
		}			
		
		resultJson.put("resultCode", result);
		
		resultJson.put("successToken", token);
		
		return resultJson.toString();
	}
	
	/**
	 * 新增用户
	 * @param userName
	 * @param password
	 * @return 是否成功json字符串
	 */
	public String addUser(String userName,String password){
		
		JSONObject resultJson=new JSONObject();
		
		boolean result=false;
		
		String resultData="";
		
		UserEntity user=new UserEntity();
		
		try{	
														
			user.setUserName(userName);
			
			user=userMapper.getUser(user);
					
			if(user!=null){
				
				result=false;
				
				resultData="用户名已存在";
				
			}else{
				
				user=new UserEntity();
				
				UUID uuid=UUID.randomUUID();

				String mdPasswordString=MD5Util.Md5Encrypt(password);
				
				user.setId(uuid.toString());
				
				user.setUserName(userName);
				
				user.setPassword(mdPasswordString);
				
				user.setIsSys("0");				
				
				userMapper.insertUser(user);
				
				result=true;
				
				resultData="成功";
			}
				
		}catch(Exception e){
			
			resultData="存在异常,异常为"+e.getMessage();
		}
		
		resultJson.put("resultCode", result);
		
		resultJson.put("resultData", resultData);
		
		return resultJson.toString();
				
	}
	

	/**
	 * 删除用户
	 * @param id
	 * @return 是否成功json字符串
	 */
	public String delUser(String id) {
		
		JSONObject resultJson=new JSONObject();
		
		boolean result=false;
		
		try{
						
			userMapper.deleteUser(id);	
			
			result=true;
			
		}catch(Exception e){
			
		}
		
		resultJson.put("resultCode", result);
		
		return resultJson.toString();
	}
	

}
