package like.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import like.entity.LogEntity;
import like.mapper.LogsMapper;
import like.service.LogsService;

/**
 * 日志接口实现类
 * @author like
 *
 */
public class LogsServiceImpl implements LogsService{
	
	@Autowired
	private LogsMapper logsMapper;
	
	/**
	 * 新增日志方法
	 */
	@Override
	public void addLogs(String ip, String type, String content, String path) {
		
		LogEntity log=new LogEntity();
		
		UUID uuid=UUID.randomUUID();
		
		Date date=new Date();
		
		SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		String nowDate=sf.format(date);
		
		log.setId(uuid.toString());
		
		log.setIp(ip);
		
		log.setTime(nowDate);
		
		log.setType(type);
		
		log.setContent(content);
		
		log.setPath(path);
		
		logsMapper.insertLogs(log);
		
	}

}
