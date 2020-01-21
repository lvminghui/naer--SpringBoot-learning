package com.sxbang.friday.service;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dto.RoleDto;
import com.sxbang.friday.model.SysRole;

public interface RoleService {

	Results<SysRole> getAllRoles();

	Results<SysRole> getAllRolesByPage(Integer offset, Integer limit);

	Results<SysRole> save(RoleDto roleDto);

	SysRole getRoleById(Integer id);

	Results update(RoleDto roleDto);

	Results delete(Integer id);

	Results<SysRole> getRoleByFuzzyRoleNamePage(String roleName, Integer offset, Integer limit);
}
