package pl.surveyapplication.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.surveyapplication.filter.MyFilter;

/**
 * @author Dawid
 * @version 1.0
 * Klasa odpowiadająca za filtry (inaczej mówiąc logi) w naszej aplikacji
 * */
@Configuration
public class MyFilterConfig {

    /**
     * @return registraionBean czyli klase która będzie odpowiedzialna za monitorowanie co się aktualnie dzieje w danych endpoincie.
     * */
    @Bean
    public FilterRegistrationBean<MyFilter> registrationBean(){
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/survey/*");

        return registrationBean;
    }

}
