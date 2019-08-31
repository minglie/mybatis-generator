package com.winit.generator.task;

import com.winit.generator.Constants;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.DaoHandler;
import com.winit.generator.handler.impl.QueryHandler;
import com.winit.generator.model.QueryInfo;
import com.winit.generator.model.QueryInfo;

import java.util.ArrayList;
import java.util.List;

public class QueryTask extends AbstractApplicationTask {
    private static String QUERY_FTL = "template/Query.ftl";
    
    private List<QueryInfo> queryInfos;
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成Query");
        
        queryInfos = (List<QueryInfo>) context.getAttribute("queryList");
        
        BaseHandler<QueryInfo> handler = null;
        for (QueryInfo queryInfo : queryInfos) {
            handler = new QueryHandler(QUERY_FTL, queryInfo);
            handler.execute();
        }
        
        logger.info("生成Query完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
    }

}
