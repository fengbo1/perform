package perform.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ccb.hibernate.HibernateSessionFactory;

import perform.flag.dao.PFlagDAO;

public class AuthorityFilter implements Filter{

	private static Logger logger = Logger.getLogger(AuthorityFilter.class);
	private FilterConfig filterConfig;
    //初始化方法实现
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    //身份认证的过滤
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	PFlagDAO pfdao = new PFlagDAO();
    	Session s = HibernateSessionFactory.getSession();
    	Transaction t=s.beginTransaction();
    	int pflag = pfdao.findNowFlag();//查询当前打分标志
    	t.commit();
        s.flush();
        s.clear();
        s.close();
        ServletContext context = this.filterConfig.getServletContext();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String IP = (String) session.getAttribute("IP");
        String url = req.getRequestURI();
        //登录后才能进入下一步处理，否则直接进入错误提示页面
        if(url.startsWith("/perform/page")&&url.endsWith("authorityfailed.jsp"))
        {
        	 res.sendRedirect("./../authorityfailed.jsp");
        }
        else if ((session.getAttribute("username") != null)||url.endsWith("login.action")) {
            context.log("身份认证通过，进入下一步处理 ");
            if((pflag==0||pflag==3)&&(url.endsWith("_rate.action")||url.endsWith("_rate.jsp")))//状态0/3，不能访问评分功能
            {
            	context.log("未开启打分，进入失败页面 ");
            	res.sendRedirect("./page/public/viewfailed.jsp");
            }
            else if((pflag==1)&&(url.endsWith("mang.action")||url.endsWith("_mang.jsp")))//状态1，打分过程中，不能修改设置
            {
            	context.log("打分过程中，进入失败页面 ");
            	res.sendRedirect("./page/public/viewfailed2.jsp");
            }
            else if((pflag==2)&&(url.endsWith("_rate.action")||url.endsWith("_rate.jsp")||url.endsWith("mang.action")||url.endsWith("_mang.jsp")))//状态2，打分公示中，不能评分，不能修改设置
            {
            	context.log("打分过程中，进入失败页面 ");
            	res.sendRedirect("./page/public/viewfailed3.jsp");
            }
            else
            {
            	 logger.info(username+"IP:"+IP+"url:"+url+"session:"+session.toString());
                 chain.doFilter(request, response);
            }
           
        } else {
            context.log("身份认证失败，直接返回");
            logger.info(username+"IP:"+IP+"url:"+url);
            res.sendRedirect("authorityfailed.jsp");
        }
    }
    //实现销毁方法
    public void destroy() {
        this.filterConfig = null;
    }
}
