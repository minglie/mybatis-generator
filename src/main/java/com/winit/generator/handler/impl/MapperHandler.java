package com.winit.generator.handler.impl;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import com.winit.generator.handler.BaseHandler;
import com.winit.generator.config.Configuration;
import com.winit.generator.model.MapperInfo;

public class MapperHandler extends BaseHandler<MapperInfo> {
    public MapperHandler(String ftlName, MapperInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("mapperXml.path")
                + File.separator + info.getFileName() + ".xml";
        
    }
    
    @Override
    public void combineParams(MapperInfo info) {
      //<result column="SU_ROUTE_CODE" jdbcType="VARCHAR" property="suRouteCode" />
        this.param.put("namespace", info.getNamespace());
        this.param.put("entityType", info.getEntityInfo().getPackageClassName());
        this.param.put("tableName", info.getEntityInfo().getTableName());
        this.param.put("entityName", info.getEntityInfo().getEntityName());
        
        StringBuilder resultMap = new StringBuilder();
        StringBuilder baseColumn = new StringBuilder();
        StringBuilder insertIfColumns = new StringBuilder();
        StringBuilder insertIfProps = new StringBuilder();
        StringBuilder insertBatchColumns = new StringBuilder();
        StringBuilder insertBatchProps = new StringBuilder();
        StringBuilder updateColProps = new StringBuilder();
        StringBuilder updateBatchColProps = new StringBuilder();
        //resultMap
        Map<String, String> propJdbcTypes = info.getEntityInfo().getPropJdbcTypes();
        for (Entry<String, String> entry : info.getEntityInfo().getPropNameColumnNames().entrySet()) {
            String propName = entry.getKey();
            String columnName = entry.getValue();
            
            if (!("id".equals(propName))) {
                resultMap.append("\n    <result column=\"").append(columnName).append("\" jdbcType=\"")
                .append(propJdbcTypes.get(propName)).append("\" property=\"").append(propName)
                .append("\"/>");
                if ((!("created".equals(propName))) && !("createdby".equals(propName))) {
                    updateColProps.append("\n                ").append(columnName).append("=#{")
                    .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},");

                    updateBatchColProps.append("        <if test=\"item.").append(propName).append(" != null\">\r\n          ").append(columnName).append("=#{item.")
                    .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},\r\n").append("        </if>\r\n");
                }
               
            }
            baseColumn.append(columnName).append(",");
            if (!("updated".equals(propName)) && !("updatedby".equals(propName))) {
                insertIfColumns.append(columnName).append(",");
                insertIfProps.append("\n                #{")
                .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},");
                insertBatchColumns.append(columnName).append(",");
                /**
                 * #{item.id,jdbcType=BIGINT},
                 */
                insertBatchProps.append("#{item.")
                .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},");
            }
            
        }



        this.param.put("resultMap", resultMap.toString());
        this.param.put("baseColumn", baseColumn.substring(0, baseColumn.length() - 1));


        this.param.put("insertIfColumns", insertIfColumns.substring(0,insertIfColumns.length()-1));
        this.param.put("insertIfProps", insertIfProps.substring(0,insertIfProps.length()-1));
        this.param.put("insertBatchColumns", insertBatchColumns.substring(0, insertBatchColumns.length() - 1));
        this.param.put("insertBatchProps", insertBatchProps.substring(0, insertBatchProps.length() - 1));
        this.param.put("updateColProps", updateColProps.substring(0, updateColProps.length() - 1));
        this.param.put("updateBatchColProps", updateBatchColProps.toString());
    }

}
