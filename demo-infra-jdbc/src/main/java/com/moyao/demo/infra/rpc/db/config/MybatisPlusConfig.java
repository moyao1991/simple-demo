package com.moyao.demo.infra.rpc.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;

@Configuration
public class MybatisPlusConfig {


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(MybatisPlusProperties mybatisPlusProperties) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        TableNameInnerInterceptor tableNameInnerInterceptor = new TableNameInnerInterceptor((sql, tableName) -> {
            int index = tableName.indexOf("_do");
            if (index == tableName.length() - 3) {
                return tableName.substring(0, index);
            }
            return tableName;
        });
        interceptor.addInnerInterceptor(tableNameInnerInterceptor);
        interceptor.setProperties(mybatisPlusProperties.getConfigurationProperties());
        return interceptor;
    }
}