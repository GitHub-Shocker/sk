package com.sk.config.tenant;

import cn.hutool.core.collection.ListUtil;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 业务层
 *
 * @author Shocker
 * @see <a href=""></a>
 * @since 2022/9/22
 **/
@Component
@Configuration
@ConfigurationProperties(prefix = "tenant.isolated.tables")
@Getter
public class TenantTableConfig {
    /**
     * 租户表列表
     */
    private final List<String> tenantTableList = ListUtil.toList();
}
