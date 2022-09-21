package com.sk.util.dto;

import lombok.Data;

/**
 * 业务层
 *
 * @author Shocker
 * @see <a href=""></a>
 * @since 2022/9/22
 **/
@Data
public class PermissionDTO {
    private Integer url;
    private Integer method;
    private Integer name;
}
