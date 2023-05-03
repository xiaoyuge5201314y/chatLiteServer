package com.yu.chatliteserver.util;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * mybatis plus 代码生成器
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {

//        FastAutoGenerator.create("jdbc:mysql://49.233.41.177:3306/blog?useUnicode=true&serverTimezone=GMT&characterEncoding=utf-8", "blog", "qqa222888")
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/chatLite?useUnicode=true&serverTimezone=GMT&characterEncoding=utf-8", "root", "qqa222888")

                .globalConfig(builder -> {
                    builder.author("吴东宇") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()// 禁止打开输出目录，默认值:true
                            .commentDate("yyyy-MM-dd HH:mm:ss")// 注释日期，默认值: yyyy-MM-dd
                            .outputDir("./src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.yu.chatliteserver") // 设置父包名，默认值:com.baomidou
//                            .moduleName() // 设置父包模块名，默认值:无
                            .entity("entity")// Entity 包名，默认值:entity
                            .service("service")// Service 包名，默认值:service
                            .serviceImpl("service.impl")// Service Impl 包名，默认值:service.impl
                            .mapper("mapper")// Mapper 包名，默认值:mapper
                            .xml("mapper.xml")// Mapper XML 包名，默认值:mapper.xml
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "./src/main/resources/mapper")) // 路径配置信息
                            .controller("controller");// Controller 包名，默认值:controller
                })
                .strategyConfig(builder -> {
                    builder.addInclude(
                                    "tb_chat_conversation",
                                    "tb_chat_conversation_detail"
                            )
                            .addTablePrefix("db_", "tb_", "tr_")// 设置过滤表前缀
                            .entityBuilder().disableSerialVersionUID()// 禁用生成 serialVersionUID,默认值:true
                            .enableChainModel()// 开启链式模型,默认值:false
                            .enableLombok()// 开启 lombok 模型,默认值:false
                            .enableTableFieldAnnotation()// 开启生成实体时生成字段注解,默认值:false
                            .enableActiveRecord()// 开启 ActiveRecord 模型,默认值:false
//                                            .versionColumnName("version")// 乐观锁字段名(数据库)
//                                            .logicDeleteColumnName("deleted")// 逻辑删除字段名(数据库)
//                                            .addTableFills(new Column("create_time", FieldFill.INSERT))
//                                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .mapperBuilder().superClass(BaseMapper.class)// 设置父类
                            .enableMapperAnnotation()// 开启 @Mapper 注解,默认值:false
                            .enableBaseResultMap()// 启用 BaseResultMap 生成,默认值:false
                            .enableBaseColumnList()// 启用 BaseColumnList,默认值:false
                            .controllerBuilder().enableHyphenStyle()// 开启驼峰转连字符,默认值:false
                            .enableRestStyle();// 开启生成@RestController 控制器,默认值:false
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 引擎模板
                .execute();
    }
}
