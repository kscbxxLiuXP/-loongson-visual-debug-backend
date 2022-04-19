package com.loongson.debug;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        //sql相关
        String JDBCUrl = "jdbc:mysql://liuxinpeng.mysql.rds.aliyuncs.com:3306/loongson_debug?characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String username = "liuxp";
        String password = "LiuXinPeng35";

        //导出数据库表设置
        String exportTables = "lt_log_analysis";//用,隔开

        //包名
        String parentPackage = "com.loongson.debug1";
        String moduleName = "";

        //生成的路径名
        String projectPath = System.getProperty("user.dir");//项目根目录
        String codePath = projectPath + "/src/main/java";//controller,entity,mapper
        String mapperPath = projectPath + "/src/main/resources/" + parentPackage.replace(".", "/") + "/mapper";

        //生成器构建代码
        FastAutoGenerator.create(JDBCUrl, username, password).globalConfig(builder -> {
                    builder.author("liuxp") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(codePath); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent(parentPackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperPath)); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.addInclude(exportTables); // 设置需要生成的表名,用逗号隔开
                    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                }).templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
