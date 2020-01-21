package com.sxbang.friday.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dao.PermissionDao;
import com.sxbang.friday.dao.RolePermissionDao;
import com.sxbang.friday.model.SysPermission;
import com.sxbang.friday.service.PermissionService;
import com.sxbang.friday.util.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public Results<JSONArray> listAllPermission() {
        log.info(getClass().getName() + ".listAllPermission()");
        List datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }

    @Override
    public Results<SysPermission> listByRoleId(Integer roleId) {
        return Results.success(0, permissionDao.listByRoleId(roleId));
    }

    @Override
    public Results<SysPermission> getMenuAll() {
        return Results.success(0, permissionDao.findAll());
    }

    @Override
    public Results save(SysPermission sysPermission) {
        return (permissionDao.save(sysPermission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public SysPermission getSysPermissionById(Integer id) {
        return permissionDao.getSysPermissionById(id);
    }

    @Override
    public Results updateSysPermission(SysPermission sysPermission) {
        return (permissionDao.update(sysPermission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public Results delete(Integer id) {
        permissionDao.deleteById(id);
        permissionDao.deleteByParentId(id);
        return Results.success();
    }

    @Override
    public List<SysPermission> getMenu() {
        return permissionDao.findAll();
    }

    public Results getMenu(Long userId) {
        List<SysPermission> datas = permissionDao.listByUserId(userId);
        datas = datas.stream().filter(p -> p.getType().equals(1)).collect(Collectors.toList());
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }

}
