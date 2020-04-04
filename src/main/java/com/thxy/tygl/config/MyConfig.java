package com.thxy.tygl.config;


import com.thxy.tygl.component.LoginHandlerIntercetor;
import com.thxy.tygl.component.MyLocaleResolver;
import com.thxy.tygl.untils.FileSave;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

//扩展springmvc的配置
@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
            WebMvcConfigurer webMvcConfigurer2 = new WebMvcConfigurer() {
                /**
                 *  页面转发
                 * @return
                 */
                @Override
                public void addViewControllers(ViewControllerRegistry registry) {
                    registry.addViewController("/").setViewName("Login");
                    registry.addViewController("Index.html").setViewName("Index");
                    registry.addViewController("Plan.html").setViewName("/getAllPlan?nowPage=1&param=1");
                    registry.addViewController("Login.html").setViewName("Login");
                    registry.addViewController("Notice_release.html").setViewName("Notice_release");
                    registry.addViewController("Admin_add.html").setViewName("Admin_add");
                    registry.addViewController("Plan.html").setViewName("Plan");



                }

                /**
                 * addResourceHandler:指的是对外暴露的访问路径
                 * addResourceLocations指的是文件放置的目录
                 * @param registry
                 */
                @Override
                public void addResourceHandlers(ResourceHandlerRegistry registry) {

                    registry.addResourceHandler("/images/tyglFilePlan/**").addResourceLocations("file:E:/java/tygl/src/main/resources/static/images/tyglFilePlan/");
                }

                /**
                 * 添加拦截器
                 * @param registry
                 */
                @Override
                public void addInterceptors(InterceptorRegistry registry) {
                    registry.addInterceptor(new LoginHandlerIntercetor()).addPathPatterns("/**")
                    .excludePathPatterns("/").excludePathPatterns("/login").excludePathPatterns("/bootstrap/**").excludePathPatterns("/login/**")
                    .excludePathPatterns("/css/**").excludePathPatterns("/js/**").excludePathPatterns("/getAllUsers").excludePathPatterns("/bootstrap/**")
                    .excludePathPatterns("getAllUsers/css/**");
                }

            };
            return webMvcConfigurer2;
        }

    /**
     * 区域信息解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
