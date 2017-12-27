package like.entity;


/**
 * 日志记录实体类
 * @author like
 *
 */
public class LogEntity {
	
	/**
	 * 日志唯一id
	 */
	private String id;
	
	/**
	 * 日志生成时间
	 */
	private String time;
	
	/**
	 * 用户ip
	 */
	private String ip;
	
	/**
	 * 用户操作类型
	 */
	private String type;
	
	/**
	 * 用户操作内容
	 */
	private String content;
	
	/**
	 * 用户访问路径
	 */
	private String path;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
