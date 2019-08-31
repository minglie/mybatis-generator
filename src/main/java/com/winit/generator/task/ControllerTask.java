package com.winit.generator.task;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ControllerHandler;
import com.winit.generator.handler.impl.ServiceImplHandler;
import com.winit.generator.model.ControllerInfo;

import java.util.List;

public class ControllerTask extends AbstractApplicationTask {

    private static String CONTROLLER_FTL = "template/Controller.ftl";

    private List<ControllerInfo> controllerInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成Controller");

        controllerInfos = (List<ControllerInfo>) context.getAttribute("controllerList");

        BaseHandler<ControllerInfo> handler = null;
        for (ControllerInfo controllerInfo : controllerInfos) {

            handler = new ControllerHandler(CONTROLLER_FTL, controllerInfo);
            handler.execute();
        }
        logger.info("生成Controller完成");
        return false;
    }


}
