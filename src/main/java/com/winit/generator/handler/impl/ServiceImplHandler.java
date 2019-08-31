package com.winit.generator.handler.impl;

import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.ServiceImplInfo;

import java.io.File;

public class ServiceImplHandler extends BaseHandler<ServiceImplInfo> {

    public ServiceImplHandler(String ftlName, ServiceImplInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + Configuration.getString("serviceImpl.path")
                + File.separator + info.getClassName() + ".java";

    }
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    @Override
    public void combineParams(ServiceImplInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("importStr", info.getImportStr());
        this.param.put("entityDesc", info.getEntityInfo().getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("entityClassName1",toLowerCaseFirstOne(info.getEntityInfo().getClassName()));
        this.param.put("entityClassName", info.getEntityInfo().getClassName());
        this.param.put("entityName", info.getEntityInfo().getEntityName());

    }

}
