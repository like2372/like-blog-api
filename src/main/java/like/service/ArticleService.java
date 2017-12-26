package like.service;


import org.springframework.stereotype.Service;

/**
 * 文章接口
 * @author like
 *
 */
@Service
public interface ArticleService {
	
		
	/**
	 * 分页查询articleList
	 * @param start
	 * @param end
	 * @return
	 */
	public String getArticleList(String start, String end);
	
	
	/**
	 * 根据id获取article
	 * @param req
	 * @return
	 */
	public String getArticleDetail(String id) ;
	
	/**
	 * 插入文章
	 * @param articleJson
	 * @return 是否成功json字符串
	 */
	public String InsertArticleData(String articleJson);

	/**
	 * 删除文章
	 * @param articleJson
	 * @return 是否成功json字符串
	 */
	public String deleteArticleData(String articleJson) ;

	/**
	 * 修改文章
	 * @param articleJson
	 * @return 是否成功json字符串
	 */
	public String updateArticleData(String articleJson) ;

	/**
	 * 修改文章浏览量
	 * @param id
	 * @return 是否成功json字符串
	 */
	public String updateArticlePageView(String id) ;
	
	
	

	
		
}
