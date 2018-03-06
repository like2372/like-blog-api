package like.service;

import org.springframework.stereotype.Service;


/**
 * 日志操作服务
 * @author like
 *
 */
@Service
public interface LogsService {
	
	/**
	 * 新增日志
	 * @param ip
	 * @param type
	 * @param content
	 * @param path
	 */
	public void addLogs(String ip,String type,String content,String path);
	
	/**
	 * 查询日志
	 * @param startNumber
	 * @param endNumber
	 * @return
	 */
	public String getLogs(String startNumber,String endNumber,String ip,String path,String type);

}
