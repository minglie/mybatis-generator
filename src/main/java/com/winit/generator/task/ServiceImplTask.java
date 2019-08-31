package com.winit.generator.task;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ServiceHandler;
import com.winit.generator.handler.impl.ServiceImplHandler;
import com.winit.generator.model.ServiceImplInfo;

import java.util.List;

public class ServiceImplTask extends AbstractApplicationTask {

    private static String SERVICEIMPL_FTL = "template/ServiceImpl.ftl";

    private List<ServiceImplInfo> ServiceImplInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成serviceImpl");

        ServiceImplInfos = (List<ServiceImplInfo>) context.getAttribute("serviceImplList");

        BaseHandler<ServiceImplInfo> handler = null;
        for (ServiceImplInfo serviceImplInfo : ServiceImplInfos) {

            handler = new ServiceImplHandler(SERVICEIMPL_FTL, serviceImplInfo);
            handler.execute();
        }
        logger.info("生成serviceImpl完成");
        return false;
    }


}
