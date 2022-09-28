package com.sk.codeGenerator;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CodeGenerator {

    public static void main(String[] args) {
        execute();
    }

    public static void execute() {
        BuildPackage buildPackage = BuildPackage.builder()
                .dbUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true&useSSL=false")
                .dbUser("root")
                .dbPw("Shocker8250698")
                .tableName("t_user")
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

        System.out.println(buildPackage.getModuleName() + buildPackage.getJavaPath() + packageToPath(buildPackage.getVoPackage()));
        /*五、自定义参数配置*/
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //自定义变量，模板中使用：${cfg.map的key}
                Map<String, Object> map = new HashMap<>();
//                map.put("subPackageName", buildPackage.getCamelTableName());
//                map.put("adminControllerPackage", pathToPackage(buildPackage.getControllerPackage()));
//                map.put("adminVOPackage", pathToPackage(adminVOPath));
                map.put("voPackage", pathToPackage(buildPackage.getVoPackage()));
//                map.put("adminDTOPackage", pathToPackage(adminDTOPath));
//                map.put("adminServicePackage", pathToPackage(adminServicePath));
//                map.put("controllerPackage", pathToPackage(buildPackage.getControllerPackage()));
                this.setMap(map);


                /*自定义输出配置*/
                List<FileOutConfig> focList = ListUtil.toList(
                        createTemplate(buildPackage, "BaseVO.java.vm",
                                buildPackage.getModuleName() + buildPackage.getJavaPath() + packageToPath(buildPackage.getVoPackage()),
                                "VO" + StringPool.DOT_JAVA),
//                        createTemplate(
//                                "BaseAdminVO.java.vm",
//                                serviceModule + "/" + realAdminVOPath + "/",
//                                "AdminVO" + StringPool.DOT_JAVA
//                        ),
//                        createTemplate(
//                                "BaseAdminDTO.java.vm",
//                                serviceModule + "/" + realAdminDTOPath + "/",
//                                "AdminDTO" + StringPool.DOT_JAVA
//                        ),

                        createTemplate(
                                buildPackage, "mapper.xml.vm",
                                buildPackage.getModuleName() + buildPackage.getMapperXmlPackage() + packageToPath(buildPackage.getMapperPackage())+"/",
                                "Mapper" + StringPool.DOT_XML
                        )
//                         createTemplate(
//                                 "BaseController.java.vm",
//                                 gatewayModule + "/" + realControllerPath + "/",
//                                 "Controller" + StringPool.DOT_JAVA
//                         )
                );

//                if (isGeneratorAllController) {
//                    focList.add(createTemplate(
//                            "BaseAdminController.java.vm",
//                            adminModule + "/" + realAdminControllerPath + "/",
//                            "AdminController" + StringPool.DOT_JAVA
//                    ));
//                }
//                if (isGeneratorAdminService) {
//                    focList.add(createTemplate(
//                            "BaseAdminService.java.vm",
//                            serviceModule + "/" + realAdminServicePath + "/",
//                            "AdminService" + StringPool.DOT_JAVA
//                    ));
//                }
                this.setFileOutConfigList(focList);
            }
        };
        mpg.setCfg(cfg);


        mpg.execute();
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
                /*gc.setSwagger2(true);*/
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
    public static FileOutConfig createTemplate(BuildPackage buildPackage, String template,String prefix, String suffix) {
        return new FileOutConfig("/codeGenerator/" + template) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return buildPackage.getProjectPath() + "/" + prefix + tableInfo.getEntityName() + suffix;
            }
        };
    }

    /**
     * 将一个带/的相对路径转换成带.的java包路径
     *
     * @param relativePath 相对路径
     * @return 绝对路径
     */
    public static String pathToPackage(String relativePath) {
        return relativePath.replaceAll("/", ".");
    }

    public static String packageToPath(String relativePath) {
        return relativePath.replace(".", "/");
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}