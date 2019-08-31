package com.winit.generator.application.test;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.framework.Application;
import com.winit.generator.framework.context.SimpleApplicationContext;
import com.winit.generator.log.LogFactory;
import com.winit.generator.model.ColumnInfo;
import com.winit.generator.model.EntityInfo;
import com.winit.generator.model.TableInfo;
import com.winit.generator.task.InitTask;
import com.winit.generator.util.FileHelper;
import com.winit.generator.util.PropertyUtil;
import com.winit.generator.util.StringUtil;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class MingATaskTest {
    public static void main(String[] args) throws Exception {

        Logger  logger = LogFactory.getApplicationTaskLogger("1", "1");


        Application application =  new Application(MingATaskTest.class.getSimpleName());
        application.setApplicationName(MingATaskTest.class.getName());
        application.work();
        InitTask initTask = new InitTask();
        initTask.initLogger("0","0");
        SimpleApplicationContext context=new SimpleApplicationContext();
        initTask.doInternal(context);
        Map<String, Object> ctx = context.getCtx();
        //获取实体相关的配置
        String packageName = Configuration.getString("entity.package");
        //存放路径
        String path = Configuration.getString("entity.path");
        logger.info("所有实体的包名为{}， 路径为：{}", packageName, path);
        //获取表和实体的映射集合
        Map<String, String> table2Entities = (Map<String, String>) context.getAttribute("tableName.to.entityName");
        //  Map<String, String> entity2Desc = (Map<String, String>) context.getAttribute("entityName.to.desc");
        Map<String, TableInfo> tableInfos = (Map<String, TableInfo>) context.getAttribute("tableInfos");
        List<EntityInfo> entityInfos = new ArrayList<EntityInfo>();
        for (Map.Entry<String, String> entry : table2Entities.entrySet()) {
            EntityInfo entityInfo = new EntityInfo();
            //表名
            String tableName = entry.getKey();
            //实体名
            String entityName= entry.getValue();
            //表信息
            TableInfo tableInfo = tableInfos.get(tableName);

            Set<String> imports = new HashSet<String>();
            Map<String, String> propTypes = new LinkedHashMap<String, String>();
            Map<String, String> propRemarks = new LinkedHashMap<String, String>();
            Map<String, String> propJdbcTypes = new LinkedHashMap<String, String>();
            Map<String, String> propName2ColumnNames = new LinkedHashMap<String, String>();

            entityInfo.setTableName(tableName);
            entityInfo.setEntityName(entityName);
            // entityInfo.setEntityDesc(entity2Desc.get(entityName));
            entityInfo.setClassName(entityName + Constants.ENTITY_SUFFIX);
            entityInfo.setEntityPackage(packageName);
            //遍历表字段信息
            List<ColumnInfo> columns = tableInfo.getColumnList();
            for (ColumnInfo columnInfo : columns) {
                String fieldName = columnInfo.getName();
                String fieldType = columnInfo.getType();

                //通过字段名生成属性名
                String propName = StringUtil.convertFieldName2PropName(fieldName);
                String propType = PropertyUtil.getValueByKey(fieldType);

                propTypes.put(propName, propType);
                propRemarks.put(propName, columnInfo.getRemark());
                propJdbcTypes.put(propName, PropertyUtil.getValueByKey("_" + propType));
                propName2ColumnNames.put(propName, columnInfo.getName().toUpperCase());
            }
            logger.info("属性类型：{}", propTypes);
            logger.info("属性jdbcTypes：{}", propJdbcTypes);
            //获取此实体所有的类型
            Collection<String> types =  propTypes.values();

            for (String type : types) {
                if (!StringUtil.isEmpty(PropertyUtil.getValueByKey(type))) {
                    imports.add(PropertyUtil.getValueByKey(type));
                }
            }
            entityInfo.setPropTypes(propTypes);
            entityInfo.setPropRemarks(propRemarks);
            entityInfo.setPropJdbcTypes(propJdbcTypes);
            entityInfo.setPropNameColumnNames(propName2ColumnNames);
            entityInfo.setImports(imports);
            entityInfo.setPackageClassName(entityInfo.getEntityPackage() + "." + entityInfo.getClassName());
            entityInfos.add(entityInfo);
        }
        context.setAttribute("entityInfos", entityInfos);

        Map map = new HashMap();
        map.put("entityInfos",entityInfos);

        String template = FileHelper.readFileToString(MingATaskTest.class.getClassLoader().getResource("template").getPath() + "MingA.ftl");

        map.put("username", "管理员");
        map.put("date", "2012-10-12");

        System.out.println(FreemarkerTemplateUtil.freemarkerProcess(map, template)); //"你好管理员，今天是2013-09-11"




    }
}
