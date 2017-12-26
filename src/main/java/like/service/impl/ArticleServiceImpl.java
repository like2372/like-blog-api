package like.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import like.entity.ArticleEntity;
import like.mapper.ArticleMapper;
import like.service.ArticleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleMapper articleMapper;
	
	
	/**
	 * 分页查询articleList
	 * @param start
	 * @param end
	 * @return
	 */
	public String getArticleList(String start, String end){
		
		JSONObject json=new JSONObject();
		
		JSONArray jsonArray=new JSONArray();
			
		String resultCode="";
		
		String totalNumber="";
		
		try{
			
						
			if(start==null||start==""||end==null||end==""){
				
				resultCode="0";
					
				json.put("data", jsonArray);
				
				json.put("resultCode", resultCode);
				
				json.put("totalNumber", totalNumber);
				
				return json.toString();
			}
			
			List<ArticleEntity> articleList=articleMapper.getArticleList(Integer.parseInt(start),Integer.parseInt(end));													
			
			for(int i=0;i<articleList.size();i++){
				
				JSONObject rowJson=new JSONObject();
				
				ArticleEntity article=articleList.get(i);
				
				String id=article.getId()==null?"":article.getId().toString();								
				
				String articleTitle=article.getArticleTitle()==null?"":article.getArticleTitle().toString();
				
				String articleTime=article.getArticleTime()==null?"":article.getArticleTime().toString();
				
				/*if(articleTime!=null&&articleTime!=""){
					
					articleTime=articleTime.substring(0,articleTime.length()-2);
					
				}*/
				
				String articleShortContent=article.getArticleShortContent()==null?"":article.getArticleShortContent().toString();
				
				String articlePageView=article.getArticlePageView()==null?"":article.getArticlePageView().toString();
				
				rowJson.put("id", id);			
				rowJson.put("articleTitle", articleTitle);
				rowJson.put("articleTime", articleTime);
				rowJson.put("articleShortContent",articleShortContent);
				rowJson.put("articlePageView",articlePageView);
				
				jsonArray.add(rowJson);
			}
				
			totalNumber=articleMapper.getTotal();
			
			resultCode="1";
			
		}catch(Exception e){
			
			resultCode="0";
			
		}
				
		json.put("data", jsonArray);
		
		json.put("resultCode", resultCode);
		
		json.put("totalNumber", totalNumber);
		
		return json.toString();
	}
	
	
	/**
	 * 根据id获取article
	 * @param req
	 * @return
	 */
	public String getArticleDetail(String id) {
		
		JSONObject json=new JSONObject();
		
		JSONArray jsonArray=new JSONArray();
			
		String resultCode="";
		
		try{			
			
			ArticleEntity article=articleMapper.getArticleDetail(id);
									
			JSONObject rowJson=new JSONObject();														
				
			String articleTitle=article.getArticleTitle()==null?"":article.getArticleTitle().toString();
				
			String articleTime=article.getArticleTime()==null?"":article.getArticleTime().toString();
				
			/*if(articleTime!=null&&articleTime!=""){
					
				articleTime=articleTime.substring(0,articleTime.length()-2);
					
			}*/
				
			String articleShortContent=article.getArticleShortContent()==null?"":article.getArticleShortContent().toString();
				
			String articleContent=article.getArticleContent()==null?"":article.getArticleContent().toString();
				
			String articlePageView=article.getArticlePageView()==null?"":article.getArticlePageView().toString();
				
			rowJson.put("id", id);			
			rowJson.put("articleTitle", articleTitle);
			rowJson.put("articleTime", articleTime);
			rowJson.put("articleShortContent",articleShortContent);
			rowJson.put("articleContent", articleContent);
			rowJson.put("articlePageView",articlePageView);
				
			jsonArray.add(rowJson);
				
			resultCode="1";
												
		}catch(Exception e){
			
			resultCode="0";
			
		}
				
		json.put("data", jsonArray);
		
		json.put("resultCode", resultCode);
		
		return json.toString();
	}
	
	/**
	 * 插入文章
	 * @param articleJson
	 * @return 是否成功json字符串
	 */
	public String InsertArticleData(String articleJson){
			
		String resultCode="";
		
		String articleTitle="";
		
		String articleShortContent="";
		
		String articleContent="";
		
		JSONObject resultJson=new JSONObject();
		
		try{
			
			JSONObject json=JSONObject.fromObject(articleJson);
						
			articleTitle=json.getString("acticleTitle");
			
			articleShortContent=json.getString("acticleShortContent");
			
			articleContent=json.getString("acticleContent");
			
			UUID uuid=UUID.randomUUID();
			
			Date date=new Date();
			
			SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			
			String nowDate=sf.format(date);
			
			String articlePageView="0";
			
			ArticleEntity article=new ArticleEntity();
			
			article.setId(uuid.toString());
	
			article.setArticleTitle(articleTitle);
			
			article.setArticleShortContent(articleShortContent);
			
			article.setArticleContent(articleContent);
			
			article.setArticleTime(nowDate);
			
			article.setArticlePageView(articlePageView);
				
			articleMapper.insertArticle(article);
			
			resultCode="1";
						
		}catch(Exception e){
			
			resultCode="0";
		}
		
		resultJson.put("resultCode",resultCode);
		
		return resultJson.toString();
		
	}

	/**
	 * 删除文章
	 * @param articleJson
	 * @return 是否成功json字符串
	 */
	public String deleteArticleData(String articleJson) {
		
		String resultCode="";
		
		JSONObject resultJson=new JSONObject();
		
		try{
			
			JSONObject articleStringJson=JSONObject.fromObject(articleJson);
			
			String articleId=articleStringJson.getString("id");
			
			articleMapper.deleteArticle(articleId);
			
			resultCode="1";
						
		}catch(Exception e){
			
			resultCode="0";
		}
		
		resultJson.put("resultCode",resultCode);
		
		return resultJson.toString();
	}

	/**
	 * 修改文章
	 * @param articleJson
	 * @return 是否成功json字符串
	 */
	public String updateArticleData(String articleJson) {
		
		String resultCode="";
		
		JSONObject resultJson=new JSONObject();
		
		try{
			
			JSONObject json=JSONObject.fromObject(articleJson);
			
			String articleId=json.getString("acticleId");
			
			String articleTitle=json.getString("acticleTitle");
			
			String articleShortContent=json.getString("acticleShortContent");
			
			String articleContent=json.getString("acticleContent");
			
			ArticleEntity article=new ArticleEntity();
			
			article.setId(articleId);
			
			article.setArticleTitle(articleTitle);
			
			article.setArticleShortContent(articleShortContent);
			
			article.setArticleContent(articleContent);
				
			articleMapper.updateArticleData(article);
								
			resultCode="1";
						
		}catch(Exception e){
			
			resultCode="0";
		}
		
		resultJson.put("resultCode",resultCode);
		
		return resultJson.toString();
	}

	/**
	 * 修改文章浏览量
	 * @param id
	 * @return 是否成功json字符串
	 */
	public String updateArticlePageView(String id) {
		
		JSONObject resultJson=new JSONObject();
		
		String resultCode="";
		
		try{			
			
			ArticleEntity article=articleMapper.getArticleDetail(id);
			
			if(article.getId()!=null&&article.getId()!=""){
				
				String pageView=article.getArticlePageView();
				
				pageView=(Integer.valueOf(pageView)+1)+"";
				
				articleMapper.updateArticlePageView(id, pageView);
				
				resultCode=1+"";
				
			}else{
				
				resultCode=0+"";			
			}
									
		}catch(Exception e){
			
			resultCode=2+"";
			
		}
		
		resultJson.put("resultCode", resultCode);
		
		return resultJson.toString();
	}
	
	
	

	
		
}
