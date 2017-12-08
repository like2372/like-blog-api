package like.config;

import like.interceptor.UserInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/articleService/**");  //对来自/user/** 这个链接来的请求进行拦截
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/userService/**");  //对来自/user/** 这个链接来的请求进行拦截
	}
}
