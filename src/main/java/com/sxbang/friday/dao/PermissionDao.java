package com.sxbang.friday.dao;

import com.sxbang.friday.model.SysPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {

    @Select("select * from sys_permission t")
    List<SysPermission> findAll();

    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
    List<SysPermission> listByRoleId(Integer roleId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_permission(parentId, name, css, href, type, permission, sort) values(#{parentId}, #{name}, #{css}, #{href}, #{type}, #{permission}, #{sort})")
    int save(SysPermission e);

    @Select("select * from sys_permission t where t.id = #{id}")
    SysPermission getSysPermissionById(Integer id);

    int update(SysPermission e);

    @Delete("delete from sys_permission where id = #{id}")
    int deleteById(Integer id);

    @Delete("delete from sys_permission where parentId = #{parentId}")
    int deleteByParentId(Integer parentId);

    @Select("SELECT DISTINCT sp.*  " +
            "FROM sys_role_user sru " +
            "INNER JOIN sys_role_permission srp ON srp.roleId = sru.roleId " +
            "LEFT JOIN sys_permission sp ON srp.permissionId = sp.id " +
            "WHERE " +
            "sru.userId = #{userId}")
    List<SysPermission> listByUserId(@Param("userId") Long userId);
}
