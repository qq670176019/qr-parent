package com.qr.admin.controller;

import com.qr.admin.service.PermissionsService;
import com.qr.common.bean.Permissions;
import com.qr.common.bean.ResultBean;
import com.qr.common.entity.AdminEntity;
import com.qr.common.entity.PermissionsEntity;
import com.qr.common.entity.RoleEntity;
import com.qr.admin.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jascon
 * @Description:
 * @Date: 22:56 2020-01-20
 */
@RestController
@RequestMapping(value = "/rple")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionsService permissionsService;

    @RequestMapping(value = "/getRole/{id}")
    public RoleEntity getRole(@PathVariable int id){
        return roleService.getRole(id);
        /**
         * 2020-01-20 试一下ResultBean这一个
         */
    }

    /**
     * 测试使用
     * @param adminEntity
     * @return
     */
    @RequestMapping(value = "/getRoleById")
    public List<RoleEntity> getRoleById(AdminEntity adminEntity){
        //查出用户
        //查出用户属于哪些角色
        //将角色拥有的权限设置进去
        adminEntity.setId(2);
        List<RoleEntity> list=roleService.getRoleById(adminEntity);//查询出该用户所有的角色
        for (RoleEntity role:list) {//查询这些角色都有哪些权限
            List<PermissionsEntity> pl=permissionsService.getAllPermissionsByRoleId(role);
            role.setPermissions(pl);
        }
        //adminEntity.setRoles(list);//设置角色集合
        System.out.println("roles: "+adminEntity.toString());
        return list;
    }

}
