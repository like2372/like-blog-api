package like.entity;


/**
 * 用户实体类
 * @author like
 */
public class UserEntity {  
	
	/**
	 * 用户唯一Id
	 */
    private String id;
    
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 密码
     */
    private String password;

    /**
     * 是否是管理员
     */
    private String isSys;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}	
	
	
	
}  
