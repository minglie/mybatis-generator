package com.winit.generator.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.winit.generator.model.*;
import com.winit.generator.util.PropertyUtil;
import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.EntityHandler;

public class EntityTask extends AbstractApplicationTask {
    private static String ENTITY_FTL = "template/Entity.ftl";
    
    private List<EntityInfo> entityInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成实体");
        
        //获取实体信息
        entityInfos = (List<EntityInfo>) context.getAttribute("entityInfos");
        
        BaseHandler<EntityInfo> handler = null;
        for (EntityInfo entityInfo : entityInfos) {
            handler = new EntityHandler(ENTITY_FTL, entityInfo);
            handler.execute();
        }
        logger.info("生成实体类完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        
        List<DaoInfo> daoList = new ArrayList<DaoInfo>();
        List<QueryInfo> queryList = new ArrayList<QueryInfo>();
        List<VoInfo> voList = new ArrayList<VoInfo>();
        List<ServiceInfo> serviceList = new ArrayList();
        List<ServiceImplInfo> serviceImplList=new ArrayList<>();
        List<ControllerInfo> controllerList=new ArrayList<>();



        //组装Dao信息、组装Vo信息
        DaoInfo daoInfo = null;
        QueryInfo queryInfo=null;
        VoInfo voInfo = null;
        ServiceInfo serviceInfo=null;
        ServiceImplInfo serviceImplInfo=null;
        ControllerInfo controllerlInfo=null;
        
        

        StringBuilder sb=new StringBuilder();

        for (EntityInfo entityInfo : entityInfos) {
            daoInfo = new DaoInfo();
            daoInfo.setClassName(entityInfo.getEntityName() + Constants.DAO_SUFFIX);
            daoInfo.setEntityInfo(entityInfo);
            daoInfo.setImportStr("import " + entityInfo.getEntityPackage() + "." + entityInfo.getClassName() + ";");
            daoInfo.setPackageStr(Configuration.getString("dao.package"));
            daoList.add(daoInfo);

            queryInfo=new QueryInfo();
            queryInfo.setClassName(entityInfo.getEntityName() + Constants.QUERY_SUFFIX);
            queryInfo.setEntityInfo(entityInfo);
            queryInfo.setImportStr("import " + entityInfo.getEntityPackage() + "." + entityInfo.getClassName() + ";");
            queryInfo.setPackageStr(Configuration.getString("query.package"));
            queryList.add(queryInfo);
            
            
            
            serviceInfo = new ServiceInfo();
            serviceInfo.setClassName(entityInfo.getEntityName() + Constants.SERVICE_SUFFIX);
            serviceInfo.setEntityInfo(entityInfo);
            serviceInfo.setImportStr("import " + entityInfo.getEntityPackage() + "." + entityInfo.getClassName() + ";");
            serviceInfo.setPackageStr(Configuration.getString("service.package"));
            serviceList.add(serviceInfo);


            serviceImplInfo=new ServiceImplInfo();
            serviceImplInfo.setClassName(entityInfo.getEntityName()+Constants.SERVICE_IMPL_SUFFIX);
            serviceImplInfo.setEntityInfo(entityInfo);
            sb.delete(0, sb.length());
            sb.append("import " + entityInfo.getEntityPackage() + "." + entityInfo.getClassName() + ";\n");//导入实体
            sb.append("import " + serviceInfo.getPackageStr() + "." + serviceInfo.getClassName() + ";\n");//导入service接口
            serviceImplInfo.setImportStr(sb.toString());
            serviceImplInfo.setPackageStr(Configuration.getString("serviceImpl.package"));
            serviceImplList.add(serviceImplInfo);


            controllerlInfo=new ControllerInfo();
            controllerlInfo.setClassName(entityInfo.getEntityName()+Constants.CONTROLLER_SUFFIX);
            controllerlInfo.setEntityInfo(entityInfo);
            sb.delete(0, sb.length());
            sb.append("import " + entityInfo.getEntityPackage() + "." + entityInfo.getClassName() + ";\n");//导入实体
            sb.append("import " + serviceInfo.getPackageStr() + "." + serviceInfo.getClassName() + ";\n");//导入service接口
            controllerlInfo.setImportStr(sb.toString());
            controllerlInfo.setPackageStr(Configuration.getString("controller.package"));
            controllerList.add(controllerlInfo);



            voInfo = new VoInfo();
            voInfo.setPackageStr(Configuration.getString("vo.package"));
            voInfo.setClassName(entityInfo.getEntityName() + Constants.VO_SUFFIX);
            voInfo.setEntityInfo(entityInfo);
            voList.add(voInfo);
        }

        context.setAttribute("controllerList", controllerList);
        context.setAttribute("serviceImplList", serviceImplList);
        context.setAttribute("serviceList", serviceList);
        context.setAttribute("daoList", daoList);
        context.setAttribute("queryList", queryList);
        context.setAttribute("voList", voList);
    }













    
    public static void main(String[] args) {
        File file = new File("/D:\\devsoftware\\workspace\\winit-java-generator\\target\\classes\\template\\Entity.ftl");
        System.out.println(EntityTask.class.getClassLoader().getResource("").getPath());
        System.out.println(file.exists());
        
        PropertyUtil.setProperty("name", "qyk1");
        PropertyUtil.setProperty("NAME", "qyk22");
    }

}
