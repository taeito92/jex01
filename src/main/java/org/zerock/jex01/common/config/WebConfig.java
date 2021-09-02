package org.zerock.jex01.common.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.zerock.jex01.board.config.BoardRootConfig;
import org.zerock.jex01.board.config.BoardServletConfig;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

@Log4j2
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {

        log.info("1----------------------------------1");
        log.info("1----------------------------------1");

        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        log.info("2----------------------------------2");
        log.info("2----------------------------------2");

        return new Class[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() { //frontController 영역
        return new String[]{"/"}; //말그대로 서블릿맵핑이므로 모든 서블릿 접근
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        //URL를 잘못작성한 경우 발생한 예외를 찾아서 예외처리 하는 exception으로 보내는 것
    }

    @Override
    //한글처리 코딩 방식
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter utf8Filter = new CharacterEncodingFilter();
        utf8Filter.setEncoding("UTF-8");
        utf8Filter.setForceEncoding(true);

        return new Filter[]{utf8Filter}; //get방식은 한글이 처리 안될 수 있음
    }
}
