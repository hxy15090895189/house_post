package com.example.house_post.MpGenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class MpGenerator {


    public static void main(String[] args) {
        generate(null,new String[] { "house","street","type","users","type","district"});
    }

    private static void generate(String moduleName,String... tableNames) {

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取项目根目录
        gc.setOutputDir(projectPath + "/src/main/java");//设置生成文件输出路径
//        gc.setOutputDir("d:/codeGen"); //自定义输出路径
        gc.setActiveRecord(false);// 是否支持 AR
        gc.setAuthor("yang"); //设置作者名字
        gc.setFileOverride(false); //默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
        gc.setIdType(IdType.AUTO);//主键策略 自增
        gc.setBaseResultMap(true); //SQL 映射文件 生成通用查询映射结果
        gc.setBaseColumnList(true);//SQL 片段  生成通用查询结果列
        gc.setOpen(false);//是否打开生成文件的目录
        gc.setSwagger2(true); //是否加入Swagger2

        //gc.setEntityName("%sEntity");			//实体命名方式  默认值：null 例如：%sEntity 生成 UserEntity
        gc.setMapperName("%sMapper");			//mapper 命名方式 默认值：null 例如：%sDao 生成 UserDao
        gc.setXmlName("%sMapper");				//Mapper xml 命名方式   默认值：null 例如：%sDao 生成 UserDao.xml
        gc.setServiceName("%sService");			//service 命名方式   默认值：null 例如：%sBusiness 生成 UserBusiness
        gc.setServiceImplName("%sServiceImpl");	//service impl 命名方式  默认值：null 例如：%sBusinessImpl 生成 UserBusinessImpl
        gc.setControllerName("%sController");	//controller 命名方式    默认值：null 例如：%sAction 生成 UserAction

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/lethouse?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDbType(DbType.MYSQL);//设置数据库类型是mysql
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example.house_post");//配置父包路径
        String parent = pc.getParent();
        String replace = parent.replace(".", "/");


        //pc.setModuleName(moduleName);//配置模块名
        //设置各层的包名
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setController("controller");
        //.setServiceImpl("service.impl"); 会自动生成 impl，可以不设定


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/java/"+replace+"/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        //templateConfig.setController("templates/controller.java");这是自定义Controller模板
        templateConfig.setXml(null);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//设置命名规则  underline_to_camel 底线变驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//设置设置列命名  underline_to_camel 底线变驼峰
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");//设置继承类
        strategy.setEntityLombokModel(true);//是否加入lombok
        strategy.setRestControllerStyle(true);//是否是RestController风格
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");//设置继承类
        strategy.setInclude(tableNames);//设置表名
        //strategy.setSuperEntityColumns("id");//设置超级列
        strategy.setControllerMappingHyphenStyle(true);//设置controller映射联字符
        //strategy.setTablePrefix(pc.getModuleName() + "_");//表的前缀

        // 生成配置
        AutoGenerator mpg = new AutoGenerator();//代码生成器
        mpg.setCfg(cfg);//加入自定义配置
        mpg.setTemplate(templateConfig);//加入配置模板
        mpg.setGlobalConfig(gc);//加入全局配置
        mpg.setDataSource(dsc);//加入数据源配置
        mpg.setPackageInfo(pc);//加入包配置
        mpg.setStrategy(strategy);//加入策略配置
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();//提交生成代码
    }
}
