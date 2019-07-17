package filter;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @create: 2019-07-04 14:57
 * @author: Aner
 * @description:
 **/
public class MyOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        System.out.println("============once per request filter");
        chain.doFilter(servletRequest,servletResponse);
    }
}
