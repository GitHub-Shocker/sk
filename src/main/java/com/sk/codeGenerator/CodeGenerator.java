package com.sk.codeGenerator;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * 代码生成器
 * <dependency>
 * <groupId>com.baomidou</groupId>
 * <artifactId>mybatis-plus-generator</artifactId>
 * <version>3.5.1</version>
 * </dependency>
 * <dependency>
 * <groupId>org.apache.velocity</groupId>
 * <artifactId>velocity-engine-core</artifactId>
 * <version>2.0</version>
 * </dependency>
 * <dependency>
 *
 * @author Shocker
 * @see <a href=""></a>
 * @since 1.0.0
 **/
public class CodeGenerator {

    public static void main(String... args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.generateCode();
    }

    public void generateCode() {
        String dbUrl = "jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true&useSSL=false";
        String dbUser = "root";
        String dbPw = "Shocker8250698";
        String tableName = "t_test";
        String author = "Shocker";


        String projectPath = System.getProperty("user.dir");
        String modulePath = "";
        String javaPath = "//src//main//java";
        String outputDir = projectPath + modulePath + javaPath;


        String parentPackage = "com.sk";
        String tablePackage = "test";
        String entityPackage = "entity" + StrUtil.DOT + tablePackage;
        String controllerPackage = "controller" + StrUtil.DOT + tablePackage;
        String mapperPackage = "mapper" + StrUtil.DOT + tablePackage;
        String mapperXmlPackage = "//src//main//resources//mapper//" + tablePackage;
        String mapperXmlDir = projectPath + modulePath + mapperXmlPackage;
        String servicePackage = "service" + StrUtil.DOT + tablePackage;
        String serviceImplPackage = servicePackage + StrUtil.DOT + "impl";


        FastAutoGenerator.create(dbUrl, dbUser, dbPw)
                .globalConfig(builder -> {
                    builder.author(author)
                            .disableOpenDir()
                            .enableSwagger()
                            .fileOverride()
                            .outputDir(outputDir)
                            .dateType(DateType.TIME_PACK);
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage)
                            .controller(controllerPackage)
                            .entity(entityPackage)
                            .mapper(mapperPackage)
                            .service(servicePackage)
                            .serviceImpl(serviceImplPackage)
                            .pathInfo(MapUtil.of(OutputFile.mapperXml, mapperXmlDir)); // 设置生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder()  // 实体类设置
                            .enableLombok()  // 开启 lombok
                            .enableTableFieldAnnotation() // 实体类字段注解
                            .controllerBuilder() // Controller 设置
                            .enableRestStyle(); // 开启 RestController
                })
//            .templateConfig(builder -> {
//                builder.controller("/codeGenerator/BaseController.java.vm");
//                builder.serviceImpl("/codeGenerator/BaseService.java.vm");
//                builder.mapperXml("/codeGenerator/mapper.xml.vm");
//            })
//            .injectionConfig(consumer -> {
//                Map<String, String> customFile = MapUtil.newHashMap();
//                customFile.put("DTO.java", "/codeGenerator/BaseDTO.java.vm");
//                customFile.put("VO.java", "/codeGenerator/BaseVO.java.vm");
//                customFile.put("Client.java", "/codeGenerator/BaseClient.java.vm");
//                consumer.customFile(customFile);
//            })
                .execute();
    }

}
