package com.hungry.config;

import com.hungry.properties.AliOssProperty;
import com.hungry.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:ascrm
 * @Date:2024/4/27
 */
@Configuration
@Slf4j
public class AliOssConfiguration {

    @Bean
    public AliOssUtil aliOssUtil(AliOssProperty aliOssProperties){
        log.info("AliOssUtil工具类对象创建成功...");
        return new AliOssUtil(aliOssProperties.getEndPoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
