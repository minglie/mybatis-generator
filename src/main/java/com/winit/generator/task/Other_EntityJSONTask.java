package com.winit.generator.task;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ControllerHandler;
import com.winit.generator.handler.impl.Other_EntityJSONHandler;
import com.winit.generator.model.ControllerInfo;
import com.winit.generator.model.EntityInfo;

import java.util.List;

public class Other_EntityJSONTask extends AbstractApplicationTask {

    private List<EntityInfo> entityInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成实体JSON");

        //获取实体信息
        entityInfos = (List<EntityInfo>) context.getAttribute("entityInfos");

        BaseHandler<EntityInfo> handler = null;
        for (EntityInfo entityInfo : entityInfos) {
            handler = new Other_EntityJSONHandler("template/Other_EntityJSON.ftl", entityInfo);
            handler.execute();
        }
        logger.info("生成实体JSON完成");
        return false;
    }


}
