package com.sk.config.mybatisplus;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sk.util.dto.PermissionDTO;

import java.io.IOException;

/**
 * 业务层：越来越多类型时，可以继承AbstractJsonTypeHandler，其可以利用泛型
 *
 * @author Shocker
 * @see <a href=""></a>
 * @since 2022/9/22
 **/
public class CustomizeTypeHandler extends JacksonTypeHandler {

    public CustomizeTypeHandler(Class<?> type) {
        super(type);
    }


    @Override
    protected Object parse(String json) {
        try {
            return getObjectMapper().readValue(json, new TypeReference<PermissionDTO>() {
            });
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }
}
