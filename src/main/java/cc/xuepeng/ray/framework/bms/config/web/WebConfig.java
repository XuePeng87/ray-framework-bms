package cc.xuepeng.ray.framework.bms.config.web;

import cc.xuepeng.ray.framework.core.util.jigsaw.DefaultJigsawGenerator;
import cc.xuepeng.ray.framework.core.util.jigsaw.JigsawGenerator;
import cc.xuepeng.ray.framework.core.util.jigsaw.JigsawProperties;
import cc.xuepeng.ray.framework.core.web.httpclient.RestTemplateProperties;
import cc.xuepeng.ray.framework.core.web.security.cors.CORSAllowedProperties;
import cc.xuepeng.ray.framework.core.web.security.url.WebSecurityUrlProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

/**
 * Web应用配置。
 *
 * @author xuepeng
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * @return 创建核心组件的跨域配置对象。
     */
    @Bean
    @ConfigurationProperties("ray.framework.http.cors")
    public CORSAllowedProperties corsAllowedProperties() {
        return new CORSAllowedProperties();
    }

    /**
     * 设置跨域过滤器。
     *
     * @return 跨域过滤器。
     */
    @Bean
    @Profile("dev")
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        CORSAllowedProperties corsAllowedProperties = corsAllowedProperties();
        config.setAllowCredentials(corsAllowedProperties.isCredentials());
        // SpringBoot 2.4.0 以后使用addAllowedOriginPattern代替addAllowedOrigin
        config.addAllowedOriginPattern(corsAllowedProperties.getOrigins());
        config.addAllowedHeader(corsAllowedProperties.getHeaders());
        config.addAllowedMethod(corsAllowedProperties.getMethods());
        source.registerCorsConfiguration(corsAllowedProperties.getMapping(), config);
        return new CorsFilter(source);
    }

    /**
     * @return 创建SpringSecurity所需的Url。
     */
    @Bean
    @ConfigurationProperties("ray.framework.http.security")
    public WebSecurityUrlProperties webSecurityUrlProperties() {
        return new WebSecurityUrlProperties();
    }

    /**
     * @return 创建RestTemplate所需的配置信息。
     */
    @Bean
    @ConfigurationProperties("ray.framework.http.client")
    public RestTemplateProperties restTemplateProperties() {
        return new RestTemplateProperties();
    }

    /**
     * 创建一个ClientHttpRequestFactory工厂对象。
     *
     * @return ClientHttpRequestFactory工厂对象。
     */
    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory(RestTemplateProperties restTemplateProperties) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        if (restTemplateProperties != null) {
            if (restTemplateProperties.getProxy().isEnable()) {
                SocketAddress address = new InetSocketAddress(
                        restTemplateProperties.getProxy().getHost(),
                        restTemplateProperties.getProxy().getPort());
                factory.setProxy(new Proxy(Proxy.Type.HTTP, address));
            }
            factory.setConnectTimeout(restTemplateProperties.getConnectionTimeout());
            factory.setReadTimeout(restTemplateProperties.getReadTimeout());
        }
        return factory;
    }

    /**
     * 创建一个RestTemplate对象。
     *
     * @param factory ClientHttpRequestFactory工厂对象。
     * @return RestTemplate对象。
     */
    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    /**
     * @return 创建拼图生成器。
     */
    @Bean
    public JigsawGenerator jigsawGenerator() {
        return new DefaultJigsawGenerator(new JigsawProperties());
    }

}
