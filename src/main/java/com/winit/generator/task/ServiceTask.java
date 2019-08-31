package com.winit.generator.task;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ServiceHandler;
import com.winit.generator.model.ServiceInfo;

import java.util.List;

public class ServiceTask extends AbstractApplicationTask {

    private static String SERVICE_FTL = "template/Service.ftl";

    private List<ServiceInfo> serviceInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成service");

        serviceInfos = (List<ServiceInfo>) context.getAttribute("serviceList");

        BaseHandler<ServiceInfo> handler = null;
        for (ServiceInfo ServiceInfo : serviceInfos) {
            handler = new ServiceHandler(SERVICE_FTL, ServiceInfo);
            handler.execute();
        }
        logger.info("生成service完成");
        return false;
    }


    @Override
    protected void doAfter(ApplicationContext context) throws Exception {

        super.doAfter(context);

    }




}
