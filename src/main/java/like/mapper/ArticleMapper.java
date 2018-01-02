package like.mapper;

import java.util.List;

import like.entity.ArticleEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ArticleMapper {
	
	@Select("SELECT id,article_title,article_time,article_tag,article_short_content,article_page_view FROM article ORDER BY article_time DESC LIMIT #{start} , #{end} ")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="articleTitle",column="article_title"),
		@Result(property="articleTime",column="article_time"),
		@Result(property="articleTag",column="article_tag"),
		@Result(property="articleShortContent",column="article_short_content"),
		@Result(property="articlePageView",column="article_page_view"),
	})
	List<ArticleEntity> getArticleList(@Param("start")int start,@Param("end")int end);
	
	@Select("SELECT COUNT(*) AS total FROM article")	
	String getTotal();
	
	@Select("SELECT * from article where id=#{id}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="articleTitle",column="article_title"),
		@Result(property="articleTime",column="article_time"),
		@Result(property="articleTag",column="article_tag"),
		@Result(property="articleContent",column="article_content"),		
		@Result(property="articleShortContent",column="article_short_content"),
		@Result(property="articlePageView",column="article_page_view"),
	})
	ArticleEntity getArticleDetail(String id);
	
	@Insert("insert into article (id,article_title,article_time,article_tag,article_short_content,article_content,article_page_view) values(#{id},#{articleTitle},#{articleTime},#{articleTag},#{articleShortContent},#{articleContent},#{articlePageView})")
	void insertArticle(ArticleEntity article);
	
	@Delete("delete from article where id=#{id}")
	void deleteArticle(String id);
	
	@Update("update article set article_title=#{articleTitle} ,article_tag=#{articleTag}, article_short_content=#{articleShortContent} , article_content=#{articleContent} where id=#{id}")
	void updateArticleData(ArticleEntity article);
	
	@Update("update article set article_page_view =#{pageView} where id=#{id}")
	void updateArticlePageView(@Param("id")String id,@Param("pageView")String pageView);
	
}
