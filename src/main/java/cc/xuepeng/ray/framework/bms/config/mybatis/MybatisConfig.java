package cc.xuepeng.ray.framework.bms.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置信息。
 *
 * @author xuepeng
 */
@Configuration
@MapperScan(basePackages = "cc.xuepeng.ray.framework.sdk.mgt.mapper")
public class MybatisConfig {

    /**
     * @return 分页插件。
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        //你的最大单页限制数量，默认 100 条，小于 0 如 -1 不受限制
        paginationInterceptor.setMaxLimit(100L);
        return paginationInterceptor;
    }

    /**
     * @return 分页插件。
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(paginationInterceptor());
        return interceptor;
    }

}
