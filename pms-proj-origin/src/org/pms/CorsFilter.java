package org.pms;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决跨域问题
 *
 * @Date 2023-02-16
 * @Author zqx
 */
@WebFilter("/*")
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("跨域访问--初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 向下转换类型
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 允许所有的域名
        String origin = req.getHeader("origin");
        resp.setHeader("Access-Control-Allow-Origin", origin);


        // 过滤器放行
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("跨域访问--销毁");
    }
}
