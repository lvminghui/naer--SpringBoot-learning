package com.sxbang.friday.controller;

import com.sxbang.friday.base.result.PageTableRequest;
import com.sxbang.friday.base.result.Results;
import com.sxbang.friday.dto.RoleDto;
import com.sxbang.friday.model.SysRole;
import com.sxbang.friday.service.RoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("/all")
	@ResponseBody
    @ApiOperation(value = "获取所有角色", notes = "获取所有角色信息")//描述
	public Results<SysRole> getAll() {
		return roleService.getAllRoles();
	}

	@GetMapping("/list")
	@ResponseBody
    @PreAuthorize("hasAuthority('sys:role:query')")
    @ApiOperation(value = "分页获取角色", notes = "用户分页获取角色信息")//描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "limit", required = true,dataType = "Integer"),
    })
	public Results list(PageTableRequest request) {
		log.info("RoleController.list(): param ( request = " + request +" )");
		request.countOffset();
		return roleService.getAllRolesByPage(request.getOffset(), request.getLimit());
	}

    @GetMapping(value = "/add")
    @PreAuthorize("hasAuthority('sys:role:add')")
    @ApiOperation(value = "新增角色信息页面", notes = "跳转到角色信息新增页面")//描述
    public String addRole(Model model) {
        model.addAttribute("sysRole",new SysRole());
        return "role/role-add";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:add')")
    @ApiOperation(value = "保存角色信息", notes = "保存新增的角色信息")//描述
    @ApiImplicitParam(name = "roleDto",value = "角色信息实体类", required = true,dataType = "RoleDto")
    public Results saveRole(@RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @GetMapping(value = "/edit")
    @ApiOperation(value = "编辑角色信息页面", notes = "跳转到角色信息编辑页面")//描述
    @ApiImplicitParam(name = "role",value = "角色信息实体类", required = true,dataType = "SysRole")
    public String editRole(Model model, SysRole role) {
        model.addAttribute("sysRole",roleService.getRoleById(role.getId()));
        return "role/role-edit";
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @ApiOperation(value = "保存角色信息", notes = "保存被编辑的角色信息")//描述
    @ApiImplicitParam(name = "roleDto",value = "角色信息实体类", required = true,dataType = "RoleDto")
    public Results updateRole(@RequestBody RoleDto roleDto) {
        return roleService.update(roleDto);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:del')")
    @ApiOperation(value = "删除角色信息", notes = "删除角色信息")//描述
    public Results<SysRole> deleteRole(RoleDto roleDto) {
        return roleService.delete(roleDto.getId());
    }

    String pattern = "yyyy-MM-dd";
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器
    }

    @GetMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:role:query')")
    @ApiOperation(value = "模糊查询角色信息", notes = "模糊搜索查询角色信息")//描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName",value = "模糊搜索的角色名", required = true),
    })
    public Results findRoleByFuzzyRoleName(PageTableRequest requests, String roleName) {
        requests.countOffset();
        return roleService.getRoleByFuzzyRoleNamePage(roleName,requests.getOffset(),requests.getLimit());
    }

}
