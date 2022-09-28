package com.sk.codeGenerator;


import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {

    public static void main(String[] args) {
        execute();
    }

    public static void execute() {
        BuildPackage buildPackage = BuildPackage.builder()
            .dbUrl(
                "jdbc:mysql://192.168.31.59:3306/whatsapp?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true&useSSL=false")
            .dbUser("yclouduser")
            .dbPw("jNxJPR7RLnfx.hZZ")
            .tableName("t_test")
            .author("Shocker")
            .moduleName("")
            .parentPackage("com.sk")
            .voPackage("vo")
            .dtoPackage("dto")
            .entityPackage("entity")
            .controllerPackage("controller")
            .mapperPackage("mapper")
            .mapperXmlPackage("mapper")
            .servicePackage("service")
            .serviceImplPackage("impl")
            .build();


        /*一、创建代码生成器：模板引擎默认为velocity*/
        AutoGenerator mpg = new AutoGenerator();
        /*配置：数据源*/
        configDataSource(buildPackage, mpg);
        /*配置：模板*/
        configTemplate(mpg);
        /*配置：策略*/
        configStrategy(buildPackage, mpg);
        /*配置：全局*/
        configGlobal(buildPackage, mpg);
        /*配置：包名*/
        configPackageName(buildPackage, mpg);
        /*自定义：配置*/
        customizeConfig(buildPackage, mpg);

        mpg.execute();
    }

    private static void customizeConfig(BuildPackage buildPackage, AutoGenerator mpg) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //自定义变量，模板中使用：${cfg.key}
                Map<String, Object> map = new HashMap<>();
                map.put("voPackage", buildPackage.getVoPackage());
                map.put("dtoPackage", buildPackage.getDtoPackage());
                this.setMap(map);


                /*自定义输出配置*/
                List<FileOutConfig> focList = ListUtil.toList(
                    createTemplate(buildPackage, "BaseVO.java.vm",
                        buildPackage.getModuleName() + buildPackage.getJavaPath() + buildPackage.getVoPackagePath(),
                        "VO" + StringPool.DOT_JAVA),
                    createTemplate(
                        buildPackage, "mapper.xml.vm",
                        buildPackage.getModuleName() + buildPackage.getMapperXmlPackage() + "/",
                        "Mapper" + StringPool.DOT_XML
                    ),
                    createTemplate(buildPackage, "BaseDTO.java.vm",
                        buildPackage.getModuleName() + buildPackage.getJavaPath() + buildPackage.getDtoPackagePath(),
                        "DTO" + StringPool.DOT_JAVA
                    ),
                    createTemplate(buildPackage, "BaseController.java.vm",
                        buildPackage.getModuleName() + buildPackage.getJavaPath() + buildPackage.getControllerPackagePath(),
                        "Controller" + StringPool.DOT_JAVA
                    ),
                    createTemplate(buildPackage, "BaseServiceImpl.java.vm",
                        buildPackage.getModuleName() + buildPackage.getJavaPath() + buildPackage.getServiceImplPackagePath(),
                        "ServiceImpl" + StringPool.DOT_JAVA
                    ),
                    createTemplate(buildPackage, "BaseServiceImpl.java.vm",
                        buildPackage.getModuleName() + buildPackage.getJavaPath() + buildPackage.getServicePackagePath(),
                        "Service" + StringPool.DOT_JAVA
                    ),
                    createTemplate(buildPackage, "BaseClient.java.vm",
                        buildPackage.getModuleName() + buildPackage.getJavaPath() + buildPackage.getControllerPackagePath(),
                        "Client" + StringPool.DOT_JAVA
                    )
                );
                this.setFileOutConfigList(focList);
            }
        };
        mpg.setCfg(cfg);
    }

    private static void configPackageName(BuildPackage buildPackage, AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        pc.setParent(buildPackage.getParentPackage())
            .setEntity(buildPackage.getEntityPackage())
            .setService(buildPackage.getServicePackage())
            .setServiceImpl(buildPackage.getServiceImplPackage())
            .setMapper(buildPackage.getMapperPackage());
        mpg.setPackageInfo(pc);
    }

    private static void configGlobal(BuildPackage buildPackage, AutoGenerator mpg) {
        GlobalConfig gc = new GlobalConfig();
        /*是否打开输出路径*/
        gc.setOpen(false)
            /*文件输出路径*/
            .setOutputDir(buildPackage.getProjectPath() + "/" + buildPackage.getModuleName() + buildPackage.getJavaPath())
            /*是否覆盖已有文件*/
            .setFileOverride(true)
            /*XML是否需要BaseResultMap*/
            .setBaseResultMap(true)
            /*XML是否需要显示字段*/
            .setBaseColumnList(true)
            /*开发人员*/
            .setAuthor(buildPackage.getAuthor())
            /*实体字段是否生成Swagger2注解*/
            .setSwagger2(true)
            /*文件命名：%s 会自动填充表实体命名*/
            .setControllerName("%sController")
            .setServiceName("%sService")
            .setServiceImplName("%sServiceImpl")
            .setMapperName("%sMapper")
            .setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);
    }

    private static void configDataSource(BuildPackage buildPackage, AutoGenerator mpg) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL)
            .setDriverName("com.mysql.jdbc.Driver")
            .setUrl(buildPackage.getDbUrl())
            .setUsername(buildPackage.getDbUser())
            .setPassword(buildPackage.getDbPw());
//                .setTypeConvert(new MySqlTypeConvert() {
//                    @Override
//                    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                        //将数据库中datetime转换成date
//                        if (fieldType.toLowerCase().contains("datetime")) {
//                            return DbColumnType.LOCAL_DATE_TIME;
//                        }
//                        return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
//                    }
//                });
        mpg.setDataSource(dsc);
    }

    private static void configTemplate(AutoGenerator mpg) {
        TemplateConfig templateConfig = new TemplateConfig();
        //此处屏蔽xml和controller的默认生成，使用自定义生成
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setService(null);
        mpg.setTemplate(templateConfig);
    }

    private static void configStrategy(BuildPackage buildPackage, AutoGenerator mpg) {
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude(buildPackage.getTableName())
            /*去除表前缀*/
            .setTablePrefix("t_")
            /*表名生成策略*/
            .setNaming(NamingStrategy.underline_to_camel)
            /*字段名生成策略*/
            .setColumnNaming(NamingStrategy.underline_to_camel)
            /*实体类是否采用构建者模式*/
            .setEntityBuilderModel(true)
            /*实体类是否采用Lombok*/
            .setEntityLombokModel(true)
            /*实体类字段是否生成注解*/
            .setEntityTableFieldAnnotationEnable(true)
            /*Controller是否采用Restful风格*/
            .setRestControllerStyle(true);
        mpg.setStrategy(sc);
    }

    /**
     * 根据一个codeGenerator下的模板名，一个输出路径和一个后缀返回一个自定义文件生成配置
     *
     * @param template 模板
     * @param prefix   前缀
     * @param suffix   后缀
     * @return 文件输出配置
     */
    public static FileOutConfig createTemplate(BuildPackage buildPackage, String template, String prefix,
        String suffix) {
        return new FileOutConfig("/codeGenerator/" + template) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return buildPackage.getProjectPath() + "/" + prefix + tableInfo.getEntityName() + suffix;
            }
        };
    }

}