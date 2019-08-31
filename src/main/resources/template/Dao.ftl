package ${packageStr};

${importStr}
import ${M_basic_package}.common.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * @author WangPengFei
 * @date ${time}
 *
 */
@Mapper
public interface ${className}{

   /**
     * 添加
     * @param ${entityClassName1}
     * @return
     */
     Integer insert(${entityClassName}  ${entityClassName1});

    /**
     * 修改
     * @param ${entityClassName1}
     * @return
     */
    Integer update(${entityClassName}  ${entityClassName1});

    /**
     * 删除
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 假删除
     * @param id
     * @return
     */
    Integer markDelete(Integer id);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    ${entityClassName} getById(Integer id);

    /**
     * 获取所有
     * @param ${entityClassName1} 模糊查询条件
     * @return
     */
    List<${entityClassName}> listAll(${entityClassName}  ${entityClassName1});

    /**
     * 分页查询
     * @param ${entityClassName1} 模糊查询条件
     * @param page
     * @return
     */
    List<${entityClassName}> listByPage(@Param("${entityClassName1}")${entityClassName}  ${entityClassName1}, @Param("page")Page page);

    /**
     * 根据${entityClassName1}模糊查询个数
     * @param ${entityClassName1} 模糊查询条件
     * @return
     */
    Integer count(@Param("${entityClassName1}")${entityClassName}  ${entityClassName1});

    /**
     * 批量删除
     * @param ids 需要删除的id数组
     * @return
     */
    Integer deleteAllByIds(int[] ids);
}