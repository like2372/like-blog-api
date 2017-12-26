package like.mapper;

import java.util.List;

import like.entity.Article;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ArticleMapper {
	
	@Select("SELECT id,article_title,article_time,article_short_content,article_page_view FROM article ORDER BY article_time DESC LIMIT #{start} , #{end} ")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="articleTitle",column="article_title"),
		@Result(property="articleTime",column="article_time"),
		@Result(property="articleShortContent",column="article_short_content"),
		@Result(property="articlePageView",column="article_page_view"),
	})
	List<Article> getArticleList(String start,String end);
	
	@Select("SELECT COUNT(*) AS total FROM article")	
	String getTotal();
	
	@Select("SELECT * from article where id=#{id}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="articleTitle",column="article_title"),
		@Result(property="articleTime",column="article_time"),
		@Result(property="articleContent",column="article_content"),		
		@Result(property="articleShortContent",column="article_short_content"),
		@Result(property="articlePageView",column="article_page_view"),
	})
	Article getArticleDetail(String id);
	
	@Insert("insert into article (id,article_title,article_time,article_short_content,article_content,article_page_view) values(?,?,?,?,?,?)")
	void insertArticle(Article article);
	
	@Delete("delete from article where id=#{id}")
	void deleteArticle(String id);
	
	@Update("update article set article_title=? , article_short_content=? , article_content=? where id=?")
	void updateArticleData(Article article);
	
	@Update("update article set article_page_view =? where id=?")
	void updateArticlePageView(String id);
	
}
