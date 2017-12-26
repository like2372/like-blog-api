package like.service;

import org.springframework.stereotype.Service;

/**
 * 用户接口类
 * @author like
 *
 */
@Service
public interface UserService {
	
	
	/**
	 * 验证用户是否存在
	 * @param userName
	 * @param password
	 * @return 是否成功json字符串
	 */
	public String checkUser(String userName,String password);

	
	/**
	 * 新增用户
	 * @param userName
	 * @param password
	 * @return 是否成功json字符串
	 */
	public String addUser(String userName,String password);
		
	/**
	 * 删除用户
	 * @param id
	 * @return 是否成功json字符串
	 */
	public String delUser(String id);
		
		
	

}
