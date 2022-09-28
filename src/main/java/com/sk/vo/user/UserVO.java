
import lombok.Builder;import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * VO
 * @author Shocker
 * @date 2022-09-23
 */
@Data
@Builder
public class VO {
        
    /**
     * ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 手机
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 权限
     */
    private String permission;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;
    }
