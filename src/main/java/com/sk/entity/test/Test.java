package com.sk.entity.test;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.sk.util.dto.PermissionDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Shocker
 * @since 2022-09-22
 */
@Getter
@Setter
@TableName(value = "t_test", autoResultMap = true)
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("age")
    private Integer age;

    @TableField("tenant_id")
    private Integer tenantId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private PermissionDTO permission;

//    @TableField(typeHandler = ListTypeHandler.class)
//    private List<PermissionDTO> permission;

    @TableField(value = "count(phone)", insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER, select = false)
    private Integer count;

}
