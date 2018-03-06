package like.mapper;

import java.util.List;
import java.util.Map;

import like.entity.LogEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;

public interface LogsMapper {
	
	@Select("<script>"
			+ "SELECT * FROM bloglogs WHERE 1=1 "
			+ "<if test='ip!=null'> and ip=#{ip} </if>"
			+ "<if test='path!=null'> and path=#{path} </if>"
			+ "<if test='type!=null'> and type=#{type} </if>"
			+ "ORDER BY time DESC "
			+ "<if test='start!=null and limit!=null'> LIMIT #{start} , #{limit} </if>"
			+ "</script>")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="time",column="time"),
		@Result(property="ip",column="ip"),
		@Result(property="type",column="type"),
		@Result(property="content",column="content"),
		@Result(property="path",column="path")
	})
	List<LogEntity> getLogsList(Map<String,Object> paramMap);
	
	@Select("<script>SELECT count(*) FROM bloglogs where 1=1 "
			+ "<if test='ip!=null'> and ip=#{ip} </if>"
			+ "<if test='path!=null'> and path=#{path} </if>"
			+ "<if test='type!=null'> and type=#{type} </if>" 
			+"</script>")	
	String getLogsTotalNumber(Map<String,Object> paramMap);
	
	@Insert("INSERT INTO bloglogs (id,time,ip,type,content,path) VALUES(#{id},#{time},#{ip},#{type},#{content},#{path})")
	void insertLogs(LogEntity log);
	
	@Delete("DELETE FROM bloglogs WHERE id=#{id}")
	void deleteLogs(String id);
	
	
}
