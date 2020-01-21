package com.sxbang.friday.service.impl;

import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dao.RoleUserDao;
import com.sxbang.friday.model.SysRoleUser;
import com.sxbang.friday.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public Results getSysRoleUserByUserId(Integer userId) {
        SysRoleUser sysRoleUser = roleUserDao.getSysRoleUserByUserId(userId);
        if(sysRoleUser != null){
            return Results.success(sysRoleUser);
        }else{
            return Results.success();
        }
    }
}
