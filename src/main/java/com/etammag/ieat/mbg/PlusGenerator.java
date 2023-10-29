package com.etammag.ieat.mbg;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;

public class PlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://192.168.200.130:3306/ieat?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                        "root", "Root_123")
                .globalConfig(builder -> {
                    builder.author("etammag") // 设置作者
                            .disableOpenDir()
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D:\\CodeProject\\IntelliJ_IDEA\\iEat\\src\\main\\java") // 指定输出目录
                    ;
                })
                .packageConfig(builder -> {
                    builder.parent("com.etammag") // 设置父包名
                            .moduleName("ieat") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\CodeProject\\IntelliJ_IDEA\\iEat\\src\\main\\resources\\mapper")) // 设置mapperXml生成路径
                    ;
                })
                .strategyConfig(builder -> {
                    builder.addInclude("order_detail") // 设置需要生成的表名
                            .entityBuilder()
                                .enableLombok() //使用lombok
                                .disableSerialVersionUID()
                                //.enableTableFieldAnnotation()
                                    .addTableFills(Arrays.asList(
                                            new Column("create_time", FieldFill.INSERT),
                                            new Column("create_user", FieldFill.INSERT),
                                            new Column("update_time", FieldFill.INSERT_UPDATE),
                                            new Column("update_user", FieldFill.INSERT_UPDATE)
                                    ))
                                .logicDeleteColumnName("is_deleted")
                                .fileOverride()
                            .controllerBuilder()
                                .enableRestStyle()
                                //.fileOverride()
                            .mapperBuilder()
                                .enableMapperAnnotation()
                                .enableBaseResultMap()
                                //.enableBaseColumnList()
                                .fileOverride()
                            .serviceBuilder()
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl")
                    //.fileOverride()
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}