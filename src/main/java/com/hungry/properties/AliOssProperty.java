package com.hungry.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:ascrm
 * @Date:2024/4/27
 */
@Data
@Component
@ConfigurationProperties("hungry-take-out.ali-oss")
public class AliOssProperty {

    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
