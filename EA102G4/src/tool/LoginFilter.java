package tool;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.mem.model.MemVO;

public class LoginFilter implements Filter {
	
	ServletContext context = null;
	
	public void init (FilterConfig config) {
		context = config.getServletContext();
	}
	
	public void destory() {
		context = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVOLogin");
		System.out.println("123");
		if(memVO == null) {
			session.setAttribute("location", req.getServletPath());
			System.out.println(req.getServletPath());
			res.sendRedirect(req.getContextPath() + "/front-end/mem/signin.jsp");
			return;
		
		}else {
			chain.doFilter(request, response);
		}	
	}
}
