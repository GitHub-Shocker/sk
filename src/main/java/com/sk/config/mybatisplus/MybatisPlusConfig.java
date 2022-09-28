//package com.sk.config.mybatisplus;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
//import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
//import com.sk.config.tenant.TenantTableConfig;
//import lombok.AllArgsConstructor;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.LongValue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
///**
// * 业务层
// *
// * @author Shocker
// * @see <a href=""></a>
// * @since 2022/9/22
// **/
//@Configuration
//@AllArgsConstructor
//public class MybatisPlusConfig {
//
//    /**
//     * 租户表配置
//     */
//    private TenantTableConfig tenantTableConfig;
//
//
//    /**
//     * mybatis + 拦截器：分页插件
//     *
//     * @return {@link MybatisPlusInterceptor}
//     */
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
//            @Override
//            public Expression getTenantId() {
//                /*用户登录，根据Token解析，获取用户信息（租户ID）*/
//                return new LongValue(1);
//            }
//
//            @Override
//            public boolean ignoreTable(String tableName) {
//                /*判断：配置的租户表集合中是否含有该表，若含有则增删改查：自动补充tenant_id字段*/
//                return !tenantTableConfig.getTenantTableList().contains(tableName);
//            }
//        }));
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        return interceptor;
//    }
//}
