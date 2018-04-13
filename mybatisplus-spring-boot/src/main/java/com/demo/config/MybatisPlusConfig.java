package com.demo.config;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.plugins.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantSqlParser;
import com.baomidou.mybatisplus.toolkit.PluginUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;

@Configuration
@MapperScan("com.demo.mapper")
public class MybatisPlusConfig {

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持

        List<ISqlParser> sqlParserList = new ArrayList<>();

        // 以下过滤方式与 @SqlParser(filter = true) 注解等效
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = PluginUtils.getMappedStatement(metaObject);

                if ("UserMapper.selectListBySQL".equals(ms.getId())) {
                    return true;
                }
                return false;
            }
        });
        return paginationInterceptor;
    }


}
