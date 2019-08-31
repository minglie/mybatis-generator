package ${packageStr};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${M_basic_package}.dao.mapper.${entityClassName}Mapper;
import ${M_basic_package}.common.model.Page;

${importStr}
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangPengFei
 * @date ${time}
 */
@Service
public class ${className}  implements ${entityClassName}Service{

     @Autowired
     ${entityClassName}Mapper ${entityClassName1}Mapper;

     @Override
     public ${entityClassName} insert(${entityClassName} ${entityClassName1}) {

        Integer insert = ${entityClassName1}Mapper.insert(${entityClassName1});
         if(insert==0) {
             return null;
         } else {
             return ${entityClassName1};
         }
     }

     @Override
     public Integer update(${entityClassName} ${entityClassName1}) {

        return ${entityClassName1}Mapper.update(${entityClassName1});
     }

     @Override
     public Integer delete(Integer id) {

        return ${entityClassName1}Mapper.delete(id);
     }

     @Override
     public Integer markDelete(Integer id) {

        return ${entityClassName1}Mapper.markDelete(id);
     }

    @Override
    public ${entityClassName} getById(Integer id) {

        return ${entityClassName1}Mapper.getById(id);
    }

    @Override
    public List<${entityClassName}> listAll(${entityClassName} ${entityClassName1}) {

       return ${entityClassName1}Mapper.listAll(${entityClassName1});
    }

    @Override
    public Map listByPage(${entityClassName} ${entityClassName1}, Page page) {

        int totalCount=${entityClassName1}Mapper.count(${entityClassName1});

        page.setTotalCount(totalCount);

        List<${entityClassName}> ${entityClassName1}List = ${entityClassName1}Mapper.listByPage(${entityClassName1}, page);

        Map map=new HashMap();

        map.put("rows", ${entityClassName1}List);

        map.put("total", totalCount);

        return map;
    }

}