package like.service;


/**
 * 日志操作服务
 * @author like
 *
 */
public interface LogsService {
	
	/**
	 * 新增日志
	 * @param ip
	 * @param type
	 * @param content
	 * @param path
	 */
	public void addLogs(String ip,String type,String content,String path);
	
	

}
