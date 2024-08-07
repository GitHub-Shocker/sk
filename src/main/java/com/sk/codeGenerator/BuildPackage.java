package com.sk.codeGenerator;

import cn.hutool.core.util.StrUtil;
import lombok.Builder;
import lombok.Getter;

/**
 * 业务层
 *
 * @author Shocker
 * @see <a href=""></a>
 * @since 2022/9/22
 **/
@Builder
@Getter
public class BuildPackage {
    public final String projectPath = System.getProperty("user.dir");
    public final String javaPath = String.format("%ssrc%smain%sjava%s", StrUtil.BACKSLASH, StrUtil.BACKSLASH, StrUtil.BACKSLASH,StrUtil.BACKSLASH);
    public final String resourcePath = String.format("%ssrc%smain%sresources", StrUtil.BACKSLASH, StrUtil.BACKSLASH, StrUtil.BACKSLASH);
    private String moduleName;

    private String outputDir;
    private String tableName;
    private String parentPackage;
    private String entityPackage;
    private String controllerPackage;
    private String mapperPackage;
    private String mapperXmlPackage;
    private String servicePackage;
    private String voPackage;
    private String dtoPackage;
    private String serviceImplPackage;
    private String dbUrl;
    private String dbUser;
    private String dbPw;
    private String author;

    public String getCamelTableName() {
        return camelTransfer(tableName);
    }

    public String getOutputDir() {
        return projectPath + moduleName + javaPath;
    }

    public String getBusinessPackage() {
        return camelTransfer(tableName);
    }

    public String getEntityPackage() {
        return entityPackage + StrUtil.DOT + this.getBusinessPackage();
    }

    public String getControllerPackage() {
        return parentPackage + controllerPackage + StrUtil.BACKSLASH + this.getBusinessPackage();
    }

    public String getControllerPackagePath() {
        return getParentPackagePath() + StrUtil.BACKSLASH  + controllerPackage + StrUtil.BACKSLASH + this.getBusinessPackage()+ StrUtil.BACKSLASH;
    }

    public String getMapperPackage() {
        return mapperPackage + StrUtil.DOT + this.getBusinessPackage();
    }

    public String getMapperXmlPackage() {
        return resourcePath + StrUtil.BACKSLASH + mapperXmlPackage + StrUtil.BACKSLASH + this.getBusinessPackage();
    }

    public String getServicePackage() {
        return servicePackage + StrUtil.DOT + this.getBusinessPackage();
    }

    public String getServicePackagePath() {
        return getParentPackagePath() + StrUtil.BACKSLASH + servicePackage + StrUtil.BACKSLASH + this.getBusinessPackage() + StrUtil.BACKSLASH;
    }

    public String getServiceImplPackage() {
        return this.getServicePackage() + StrUtil.DOT + serviceImplPackage;
    }

    public String getServiceImplPackagePath() {
        return getServicePackagePath() + serviceImplPackage + StrUtil.BACKSLASH;
    }

    public String getVoPackagePath() {
        return getParentPackagePath() + StrUtil.BACKSLASH + voPackage + StrUtil.BACKSLASH + this.getBusinessPackage() + StrUtil.BACKSLASH;
    }

    public String getVoPackage() {
        return parentPackage + StrUtil.DOT + voPackage + StrUtil.DOT + this.getBusinessPackage();
    }

    public String getParentPackagePath() {
        return packageToPath(parentPackage);
    }

    public String getDtoPackage() {
        return parentPackage + StrUtil.DOT + dtoPackage + StrUtil.DOT + this.getBusinessPackage();
    }

    public String getDtoPackagePath() {
        return getParentPackagePath() + StrUtil.BACKSLASH + dtoPackage + StrUtil.BACKSLASH + this.getBusinessPackage() + StrUtil.BACKSLASH;
    }

    /**
     * 骆驼转换
     *
     * @param tableName 表格名称
     * @return {@link String}
     */
    public String camelTransfer(String tableName) {
        if (StrUtil.isBlank(tableName)) {
            throw new RuntimeException("表名不能为空");
        }
        return StrUtil.replace(tableName, "t_", "");
    }

    public static String packageToPath(String relativePath) {
        return relativePath.replace(".", "/");
    }
}
