package pl.surveyapplication.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Dawid
 * @version 1.0
 * Klasa implementująca i zarządzająca naszymi filtrami
 * */
@Component
public class MyFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Some body want to see what is in /survey endpoints");
        chain.doFilter(request,response);
    }
}
