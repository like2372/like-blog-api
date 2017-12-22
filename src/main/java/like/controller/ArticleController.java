package like.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;

import like.config.EntityConfig;
import like.service.ArticleService;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@RestController
@EnableAutoConfiguration
@RequestMapping("/articleService")
@Api("ArticleController相关api")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private EntityConfig entityConfig;
		
	@ApiOperation("获取文章列表")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="start",dataType="String",value="开始的数量",defaultValue="0"),
		@ApiImplicitParam(paramType="query",name="end",dataType="String",value="结束的的数量",defaultValue="6")
	})
	@ApiResponses({
		@ApiResponse(code=400,message="请求参数没有填好"),
		@ApiResponse(code=404,message="请求路径不对")
	})
	@RequestMapping(value="/getArticleList",method=RequestMethod.GET)
	public String getArticleList(HttpServletRequest req){
		
		String start=req.getParameter("start");
		
		String end=req.getParameter("end");
		
		return articleService.getArticleList(start,end);	
	}
	
	/**
	 * 根据id获取article
	 * @param req
	 * @return
	 */
	@ApiOperation("根据id获取文章详情")
	@RequestMapping(value="/getArticleDetail",method=RequestMethod.GET)
	public String getArticleDetail(HttpServletRequest req){
		
		String id=req.getParameter("id");
		
		return articleService.getArticleDetail(id);	
	}
	
	@ApiOperation("插入新文章")
	@RequestMapping(value="/insertArticleData",method=RequestMethod.POST)
	public String InsertArticleData(HttpServletRequest req,@RequestParam  String articleJson){	
		
		return articleService.InsertArticleData(articleJson);	
	}
	
	@ApiOperation("修改文章")
	@RequestMapping(value="/updateArticleData",method=RequestMethod.POST)
	public String updateArticleData(HttpServletRequest res,@RequestParam  String articleJson){					
		
		return articleService.updateArticleData(articleJson);	
	}
	
	@ApiOperation("根据文章id删除文章")
	@RequestMapping(value="/deleteArticleData",method=RequestMethod.POST)
	public String deleteArticleData(HttpServletRequest res,@RequestParam  String articleJson){	
						
		return articleService.deleteArticleData(articleJson);	
	}
		
	@ApiOperation("根据文章id修改浏览量")
	@RequestMapping(value="/updateArticlePageView",method=RequestMethod.GET)
	public String updateArticlePageView(HttpServletRequest res){	
		
		String id=res.getParameter("id");
		
		return articleService.updateArticlePageView(id);	
	}
	
	@ApiOperation("上传文件")
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
	public String uploadFile(HttpServletRequest res){	
		
		
		JSONObject resultJson=new JSONObject();
		
		String result="";
		
		String resultCode="";
		
		try{
			
			long startTime=System.currentTimeMillis();
			
			List<MultipartFile> files = ((MultipartHttpServletRequest) res).getFiles("file");
			
			MultipartFile file = null;
			
		    BufferedOutputStream stream = null;
		    
		    for(int i=0;i<files.size();i++){
		    	
		    	file=files.get(i);
		    	
		    	if(file!=null){
		    		
		    		 byte[] bytes = file.getBytes();		    		 		    		 
		    		 
		    		 stream = new BufferedOutputStream(new FileOutputStream(new File(entityConfig.getUploadPath()+file.getOriginalFilename())));
		    		 
		    		 System.out.println(entityConfig.getUploadPath()+file.getOriginalFilename());
		    		 
		    		 stream.write(bytes);
		    		 
		    		 stream.close();
		    	}
		    } 
			
		    long endTime=System.currentTimeMillis();
		    
		    String allTime=(endTime-startTime)/1000+"ms";
		    
			resultCode="1";
				
			result="成功,总用时"+allTime;
					
		}catch(Exception e){
			
			resultCode="0";
			
			result=e.getMessage();
		}
		
		resultJson.put("resultCode", resultCode);
		
		resultJson.put("result", result);
		
		return resultJson.toString();
				
	}
}
