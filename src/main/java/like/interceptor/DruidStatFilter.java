package like.interceptor;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;


/**
 * DruidFilter
 * @author like
 *
 */

@WebFilter(filterName="druidStatFilter",urlPatterns="/",
			initParams={
		@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {

}
