package com.winit.generator;

import java.util.HashMap;
import java.util.Map;

public class GlobalTemplateVariable {

    public static Map<String, String> param = new HashMap<String, String>();

    static {
        param.put("M_project_name","aos-agri-monitoring");
        param.put("M_basic_package","com.aliyun.aos.agri.monitoring");
    }


}
