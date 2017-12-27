package like.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import like.util.JWTUtil;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Aspect
public class UserInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub		
		
	}

	/**
	 * 预处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {

		String method=req.getMethod();
		
		if("GET".equals(method)){
			return true;
		}
		
		String auth = req.getHeader("Authorization");		
		
		if (StringUtils.isEmpty(auth)) {
            responseWithError(res);
            return false;
        }
		
        if (StringUtils.isNotEmpty(auth)) {
            boolean right = JWTUtil.validToken(auth);
            if (right) {
                return true;
            }
        }
        responseWithError(res);
        return false;
	}
	
	 /**
     * 响应错误提示
     *
     * @param response
     */
    private void responseWithError(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append("没有权限");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
     
}
