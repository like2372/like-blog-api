package like.mapper;

import like.entity.UserEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE username=#{userName}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="userName",column="username"),
		@Result(property="password",column="password"),
		@Result(property="isSys",column="isSys")
	})
	UserEntity getUser(UserEntity user); 
	
	@Select("SELECT * FROM user WHERE username=#{userName} AND password=#{password}")
	@Results({
		@Result(property="id",column="id")
	})
	UserEntity checkUser(UserEntity user); 
	
	@Insert("INSERT INTO user (id,username,password,issys) values(#{id},#{userName},#{password},#{isSys})")
	void insertUser(UserEntity user);
	
	@Delete("DELETE FROM user WHERE id=#{id}")
	void deleteUser(String id);
	
}
