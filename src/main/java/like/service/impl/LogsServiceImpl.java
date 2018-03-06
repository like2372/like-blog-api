package like.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import like.entity.LogEntity;
import like.mapper.LogsMapper;
import like.service.LogsService;

/**
 * 日志接口实现类
 * @author like
 *
 */
@Service
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

	@Override
	public String getLogs(String startNumber, String endNumber,String ip,String path,String type) {
		
		JSONObject resultJson=new JSONObject();
		
		JSONArray jsonarray=new JSONArray();
					
		String resultCode="";
		
		int totalNumber=0;
		
		try{
			
			int startNumberInteger=startNumber==null||startNumber==""?0:Integer.valueOf(startNumber);
			
			int endNumberInteger=endNumber==null||endNumber==""?0:Integer.valueOf(endNumber);
			
			int limitNumber=endNumberInteger-startNumberInteger;
			
			Map<String,Object> paramMap=new HashMap<String,Object>();
			
			paramMap.put("start", Integer.valueOf(startNumber));
			
			paramMap.put("limit", Integer.valueOf(limitNumber));
			
			paramMap.put("ip", ip);
			
			paramMap.put("path", path);
			
			paramMap.put("type", type);
					
			List<LogEntity> logList=logsMapper.getLogsList(paramMap);
					
			for(int i=0;i<logList.size();i++){
				
				JSONObject rowJson=new JSONObject();
				
				LogEntity rowLog=logList.get(i);
				
				rowJson.put("ip", rowLog.getIp());
				
				rowJson.put("path", rowLog.getPath());
							
				rowJson.put("time", rowLog.getTime());
				
				rowJson.put("type", rowLog.getType());
				
				rowJson.put("content", rowLog.getContent());
				
				jsonarray.add(rowJson);
				
			}
			
			// 查询总数
			Map<String,Object> totalParamMap=new HashMap<String,Object>();
			
			totalParamMap.put("ip", ip);
			
			totalParamMap.put("path", path);
			
			totalParamMap.put("type", type);
					
			String totalNumberString= logsMapper.getLogsTotalNumber(totalParamMap);
					
			totalNumber=totalNumberString==null?0:Integer.valueOf(totalNumberString);
			
			resultCode="1";
			
		}catch(Exception e){
			
			resultCode="0";
			
			System.out.println("获取日志错误，错误信息："+e.getMessage());
			
		}
		
		resultJson.put("resultCode", resultCode);
		
		resultJson.put("totalNumber",totalNumber);
		
		resultJson.put("data", jsonarray);
		
		return resultJson.toString();
	}

}
