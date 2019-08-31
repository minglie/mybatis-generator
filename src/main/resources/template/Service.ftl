package ${packageStr};

import ${M_basic_package}.common.model.Page;
${importStr}
import java.util.List;
import java.util.Map;

/**
 * @author WangPengFei
 * @date ${time}
 */
public interface ${className} {
    /**
     * 添加
     *
     * @param ${entityClassName1}
     * @return ${entityClassName}
     */
    ${entityClassName} insert(${entityClassName} ${entityClassName1});

   /**
     * 修改
     * @param ${entityClassName1}
     * @return int
     */
    Integer update(${entityClassName} ${entityClassName1});

    /**
     * 删除
     * @param id
     * @return int
     */
    Integer delete(Integer id);

    /**
     * 假删除
     * @param id
     * @return int
     */
    Integer markDelete(Integer id);

    /**
     * 根据id获取
     * @param id
     * @return int
     */

    ${entityClassName} getById(Integer id);

    /**
     * 获取所有
     * @param ${entityClassName1} 模糊查询条件
     * @return
     */
    List<${entityClassName}> listAll(${entityClassName} ${entityClassName1});

    /**
     * 分页查询
     * @param ${entityClassName1} 模糊查询条件
     * @param page
     * @return Map
     */
    Map listByPage(${entityClassName} ${entityClassName1},Page page);


}