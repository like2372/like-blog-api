package like.entity;


/**
 * 文章实体类
 * @author like
 *
 */
public class ArticleEntity {

	/**
	 * 文章唯一id
	 */
	private String id;
	
	/**
	 * 文章标题
	 */
	private String articleTitle;
	
	/**
	 * 创建文章时间
	 */
	private String articleTime;
	
	/**
	 * 文章简介
	 */
	private String articleShortContent;
	
	/**
	 * 文章内容
	 */
	private String articleContent;
	
	/**
	 * 文章浏览量
	 */
	private String articlePageView;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(String articleTime) {
		this.articleTime = articleTime;
	}

	public String getArticleShortContent() {
		return articleShortContent;
	}

	public void setArticleShortContent(String articleShortContent) {
		this.articleShortContent = articleShortContent;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticlePageView() {
		return articlePageView;
	}

	public void setArticlePageView(String articlePageView) {
		this.articlePageView = articlePageView;
	}
	
	
}
