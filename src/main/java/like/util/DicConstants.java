package like.util;

import java.util.HashMap;
import java.util.Map;


/**
 * 字典类
 * @author Administrator
 *
 */
public class DicConstants {
	
		public final static Map<String,String> contentMap=new HashMap<String,String>(); 
		
		static{
			contentMap.put("getArticleList", "获取文章列表");
			contentMap.put("getArticleDetail", "根据id获取文章详情");
			contentMap.put("insertArticleData", "插入新文章");
			contentMap.put("updateArticleData", "修改文章");
			contentMap.put("deleteArticleData", "根据文章id删除文章");
			contentMap.put("checkUser", "检查用户是否存在");
			contentMap.put("addUser", "增加新用户");
			contentMap.put("delUser", "根据用户id删除用户");
			
		}
		
		
		public final static Map<String,String> typeMap=new HashMap<String,String>(); 
		
		static{
			typeMap.put("getArticleList", "访问主页");
			typeMap.put("getArticleDetail", "访问文章");
			typeMap.put("insertArticleData", "新增文章");
			typeMap.put("updateArticleData", "修改文章");
			typeMap.put("deleteArticleData", "删除文章");
			typeMap.put("checkUser", "登录");
			typeMap.put("addUser", "新增用户");
			typeMap.put("delUser", "删除用户");
			
		}
	
}
