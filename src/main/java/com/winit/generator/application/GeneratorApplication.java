package com.winit.generator.application;

import com.winit.generator.framework.Application;
import com.winit.generator.task.*;

//程序入口
public class GeneratorApplication {
    public static void main(String[] args) {
        Application application = new Application(GeneratorApplication.class.getSimpleName());
        application.setApplicationName(GeneratorApplication.class.getName());

        //添加任务
        application.addTask(InitTask.class)
                .addTask(CombineTask.class)
                .addTask(EntityTask.class)
                .addTask(DaoTask.class)
                .addTask(QueryTask.class)
                .addTask(MapperTask.class)
                //.addTask(VoTask.class)
                .addTask(ServiceTask.class)
                .addTask(ServiceImplTask.class)
                .addTask(ControllerTask.class);

              application.work();
    }
}
