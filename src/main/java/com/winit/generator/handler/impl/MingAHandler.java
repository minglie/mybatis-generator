package com.winit.generator.handler.impl;

import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.EntityInfo;

import java.io.File;
import java.util.List;
import java.util.Map;

public class MingAHandler extends BaseHandler<Object> {

    public MingAHandler(String ftlName, Object info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = "c:"
                + File.separator + "MingA"
                + File.separator  + "MingA"+".java";
    }


    @Override
    public void combineParams(Object Object) {


        List<EntityInfo> entityInfos=( List<EntityInfo>)Object;


      //  this.param.put("entityInfos", entityInfos);
        this.param.put("AA", "A");
       

    }

}
