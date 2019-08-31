<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}">
  <resultMap id="BaseResultMap" type="${entityType}">
    <id column="ID" jdbcType="BIGINT" property="id" /> ${resultMap}
  </resultMap>
  
  <!-- 基本列 -->
  <sql id="Base_Column_List">
    ${baseColumn}
  </sql>


  <!-- 单个插入 -->
    <insert id="insert">
        insert into ${tableName} (${baseColumn})
        values(${insertIfProps}
        )
        <selectKey resultType="java.lang.Integer" order="AFTER"
                   keyProperty="id">
            SELECT LAST_INSERT_ID()
        </selectKey>
    </insert>

  
  <!-- 单个更新 -->
  <update id="update">
    update ${tableName} set  ${updateColProps}
    where ID = <#noparse>#{id}</#noparse>
  </update>
  

  <!-- 删除 -->
  <delete id="delete">
    delete from ${tableName}
    where ID = <#noparse>#{id}</#noparse>
  </delete>

    <!--假删除-->
    <update id="markDelete">
        update ${tableName} set DELETED=1
        where ID = <#noparse>#{id}</#noparse>
    </update>

  <!-- 查询所有 -->
  <select id="listAll" resultMap="BaseResultMap">
    SELECT
    <include refid="Base_Column_List" />
    FROM ${tableName} 
  </select>


  
  <!-- 单个查询 -->
  <select id="getById"  resultMap="BaseResultMap">
      SELECT
      <include refid="Base_Column_List" />
      FROM ${tableName}
      where ID = <#noparse>#{id}</#noparse>
  </select>


    <!--分页查询  -->
    <select id="listByPage" resultMap="BaseResultMap">
        select  <include refid="Base_Column_List" />
        from  ${tableName}
        limit  <#noparse>#{page.start},#{page.offset}</#noparse>
    </select>

    <!--查询行数-->
    <select id="count" resultType="int">
        select  count(1)
        from ${tableName}
    </select>

    <!--批量删除-->
    <delete id="deleteAllByIds">
        delete from ${tableName} where id in
        <foreach  collection="array" item="i" open="(" separator="," close=")">
           <#noparse>#{i}</#noparse>
        </foreach>
    </delete>

</mapper>