package com.winit.generator.handler.impl;
import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.EntityInfo;


import java.io.File;
import java.util.Map;

public class Other_EntityJSONHandler extends BaseHandler<EntityInfo> {

    public Other_EntityJSONHandler(String ftlName, EntityInfo info) {

        info.setClassName(info.getClassName()+"JSONTest" );

        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + "entity/Other_EntityJSON"
                + File.separator  + info.getClassName()+".java";
    }
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    @Override
    public void combineParams(EntityInfo entityInfo) {
        this.param.put("packageStr", entityInfo.getEntityPackage());
        StringBuilder sb = new StringBuilder();
        for (String str : entityInfo.getImports()) {
            sb.append("import ").append(str).append(";\r\n");
        }
        this.param.put("importStr", sb.toString());
        this.param.put("entityDesc", entityInfo.getEntityDesc());
        this.param.put("className", entityInfo.getClassName());

        // 生成属性，getter,setter方法
        sb = new StringBuilder();
        StringBuilder sbMethods = new StringBuilder();

        StringBuilder createEntitys = new StringBuilder();



        Map<String, String> propRemarks = entityInfo.getPropRemarks();
        for (Map.Entry<String, String> entry : entityInfo.getPropTypes().entrySet()) {
            String propName = entry.getKey();
            String propType = entry.getValue();

            // 注释、类型、名称
            sb.append("    /**")
                    .append(propRemarks.get(propName))
                    .append("*/\r\n")
                    .append("    private ")
                    .append(propType)
                    .append(" ")
                    .append(propName)
                    .append(";\r\n");

            sbMethods.append("    public ")
                    .append(propType)
                    .append(" get")
                    .append(propName.substring(0, 1).toUpperCase())
                    .append(propName.substring(1))
                    .append("() {\r\n")
                    .append("        return ")
                    .append(propName)
                    .append(";\r\n")
                    .append("    }\r\n")
                    .append("    public void set")
                    .append(propName.substring(0, 1).toUpperCase())
                    .append(propName.substring(1))
                    .append("(")
                    .append(propType)
                    .append(" ")
                    .append(propName)
                    .append(") {\r\n")
                    .append("        this.")
                    .append(propName)
                    .append(" = ")
                    .append(propName)
                    .append(";\r\n    }\r\n")
                    .append("\r\n");


            createEntitys.append("AA");





        }

        this.param.put("propertiesStr", sb.toString());
        this.param.put("methodStr", sbMethods.toString());

        this.param.put("createEntityStr", createEntitys.toString());

    }

}
