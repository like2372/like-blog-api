package like.service;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import like.entity.User;
import like.util.Constants;
import like.util.JWTUtil;
import like.util.MD5Util;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String checkUser(String userName,String password) {
		
		JSONObject resultJson=new JSONObject();
		
		boolean result=false;
		
		String token="";
		
		try{			
			
			String mdPasswordString=MD5Util.Md5Encrypt(password);
			
			String sql="select id,username,password from user where 1=1 and username=? and password=?";
			
			if(userName!=null&&!"".equals(userName)&&mdPasswordString!=null&&!"".equals(mdPasswordString)){
				
				List<Map<String,Object>> resultMapList=jdbcTemplate.queryForList(sql, new Object[]{userName,mdPasswordString});
				
				if(resultMapList.size()!=0){
					
					String id=resultMapList.get(0).get("id").toString();
					
					result=true;
					
					User user=new User();
					
					user.setId(id);
					
					user.setUserName(userName);
					
					String subject=JWTUtil.generalSubject(user);
					
					token=JWTUtil.createToken(Constants.JWT_ID, subject, Constants.JWT_TTL);
				}
			}
			
		}catch(Exception e){
			
			result=false;
			
		}			
		
		resultJson.put("resultCode", result);
		
		resultJson.put("successToken", token);
		
		return resultJson.toString();
	}
	
	
	public String addUser(String userName,String password){
		
		JSONObject resultJson=new JSONObject();
		
		boolean result=false;
		
		String resultData="";
		
		try{	
			
			boolean sign=checkIsExist(userName);
			
			if(sign){
				
				result=false;
				
				resultData="用户名已存在";
				
			}else{
				
				String sql="insert into user (id,username,password,issys) values(?,?,?,?)";
				
				UUID uuid=UUID.randomUUID();

				String mdPasswordString=MD5Util.Md5Encrypt(password);
				
				jdbcTemplate.update(sql,new Object[]{uuid.toString(),userName,mdPasswordString,"0"});
				
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
	
	
	public boolean checkIsExist(String userName){
		
		boolean sign=false;
		
		try{
			
			String sql="select * from user where 1=1 and username=?";
			
			List<Map<String,Object>> resultMapList=jdbcTemplate.queryForList(sql,new Object[]{userName});
			
			if(resultMapList.size()>=1){
				
				sign=true;
				
			}
			
		}catch(Exception e){
			
		}
		
		return sign;	
	}


	public String delUser(String id) {
		
		JSONObject resultJson=new JSONObject();
		
		boolean result=false;
		
		try{
			
			String sql="delete from user where 1=1 and id=?";
			
			jdbcTemplate.update(sql,new Object[]{id});		
			
			result=true;
			
		}catch(Exception e){
			
		}
		
		resultJson.put("resultCode", result);
		
		return resultJson.toString();
	}
	

}
