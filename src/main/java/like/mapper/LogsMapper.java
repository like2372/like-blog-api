package like.mapper;

import java.util.List;

import like.entity.LogEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;

public interface LogsMapper {
	
	@Select("SELECT * FROM bloglogs ORDER BY time DESC LIMIT #{start} , #{end}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="time",column="time"),
		@Result(property="ip",column="ip"),
		@Result(property="type",column="type"),
		@Result(property="content",column="content"),
		@Result(property="path",column="path")
	})
	List<LogEntity> getLogsList(@Param("start")int start,@Param("end")int end);
	
	@Insert("INSERT INTO bloglogs (id,time,ip,type,content,path) VALUES(#{id},#{time},#{ip},#{type},#{content},#{path})")
	void insertLogs(LogEntity log);
	
	@Delete("DELETE FROM bloglogs WHERE id=#{id}")
	void deleteLogs(String id);
	
	
}
