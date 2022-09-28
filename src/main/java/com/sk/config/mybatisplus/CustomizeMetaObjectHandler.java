//package com.sk.config.mybatisplus;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.reflection.MetaObject;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.function.Supplier;
//
///**
// * 业务层
// *
// * @author Shocker
// * @see <a href=""></a>
// * @since 2022/9/22
// **/
//@Slf4j
//@Component
//public class CustomizeMetaObjectHandler implements MetaObjectHandler {
//
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        log.info("开始插入数据...");
//        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
//        this.strictInsertFill(metaObject, "createBy", CustomizeMetaObjectHandler::getUserId, String.class);
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        log.info("开始更新数据...");
//        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
//        this.strictUpdateFill(metaObject, "updateBy", CustomizeMetaObjectHandler::getUserId, String.class);
//    }
//
//    /**
//     * 模拟：用户登录访问，解析Token信息，将用户信息存储于Redis中，获取出来
//     *
//     * @return {@link String}
//     */
//    public static String getUserId() {
//        return "1";
//    }
//}
