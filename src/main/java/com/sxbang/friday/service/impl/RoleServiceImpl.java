package com.sxbang.friday.service.impl;

import com.sxbang.friday.base.result.ResponseCode;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dao.RoleDao;
import com.sxbang.friday.dao.RolePermissionDao;
import com.sxbang.friday.dao.RoleUserDao;
import com.sxbang.friday.dto.RoleDto;
import com.sxbang.friday.model.SysRole;
import com.sxbang.friday.model.SysRoleUser;
import com.sxbang.friday.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

    @Autowired
    RolePermissionDao rolePermissionDao;

    @Autowired
    private RoleUserDao roleUserDao;

	@Override
	public Results<SysRole> getAllRoles() {
		return Results.success(50, roleDao.getAllRoles());
	}

	@Override
	public Results<SysRole> getAllRolesByPage(Integer offset, Integer limit) {
        return Results.success(roleDao.countAllRoles().intValue(), roleDao.getAllRolesByPage(offset, limit));
	}

    @Override
    public Results<SysRole> save(RoleDto roleDto) {
        //1、先保存角色"
        roleDao.saveRole(roleDto);
        List<Long> permissionIds = roleDto.getPermissionIds();
        //移除0,permission id是从1开始
        permissionIds.remove(0L);
        //2、保存角色对应的所有权限
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), permissionIds);
        }
        return Results.success();
    }

    @Override
    public SysRole getRoleById(Integer id) {
        return roleDao.getById(id);
    }

    @Override
    public Results update(RoleDto roleDto) {
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        //1、更新角色权限之前要删除该角色之前的所有权限
        rolePermissionDao.deleteRolePermission(roleDto.getId());

        //2、判断该角色是否有赋予权限值，有就添加"
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), permissionIds);
        }

        //3、更新角色表
        int countData = roleDao.update(roleDto);

        if(countData > 0){
            return Results.success();
        }else{
            return Results.failure();
        }
    }

    @Override
    public Results delete(Integer id) {
        List<SysRoleUser> datas = roleUserDao.listAllSysRoleUserByRoleId(id);
        if(datas.size() <= 0){
            roleDao.delete(id);
            return Results.success();
        }
        return Results.failure(ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getMessage());
    }

    @Override
    public Results<SysRole> getRoleByFuzzyRoleNamePage(String roleName, Integer startPosition, Integer limit) {
        return Results.success(roleDao.countRoleByFuzzyRoleName(roleName).intValue(), roleDao.getRoleByFuzzyRoleNamePage(roleName,startPosition, limit));
    }
}
