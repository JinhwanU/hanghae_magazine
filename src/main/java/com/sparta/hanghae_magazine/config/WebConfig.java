package com.sparta.hanghae_magazine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping을 이용해서 CORS를 적용할 URL패턴을 정의
//        allowedOrigins 메소드를 이용해서 자원 공유를 허락할 Origin을 지정
//        allowedMethods를 이용해서 허용할 HTTP method를 지정
//        maxAge메소드를 이용해서 원하는 시간만큼 pre-flight 리퀘스트를 캐싱 해둘 수 있습니다.
        registry.addMapping("/**")
                .exposedHeaders("ACCESS_TOKEN")
                .exposedHeaders("REFRESH_TOKEN")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*");
    }

//    위 방법(configuration 설정) 외에도 controller에 @CrossOrigin 어노테이션을 적용하는 방법도 가능하다
//    *(:모든 출처를 허용하는 와일드카드)로 설정하는 것은 보안상 매우 안좋은 방법이다.
}
