package com.qr.admin.controller;

import com.qr.admin.service.AdminService;
import com.qr.common.bean.ResultBean;
import com.qr.common.entity.AdminEntity;
import com.qr.common.entity.RoleEntity;
import com.qr.admin.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jascon
 * @Description:
 * @Date: 8:59 2020-01-21
 */
@Controller
@RequestMapping(value = "/basicSystem")
public class BasicSystemController {
    private final static Logger logger= LoggerFactory.getLogger(BasicSystemController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminService adminService;

    //系统设置
    @RequestMapping(value = "sysSetting")
    @RequiresPermissions("sys:basicSystem_sysSetting")
    public String sysSetting(){
        return "pages/basicSystem/sysSetting";
    }
    //角色管理
    @RequestMapping(value = "role")
    public String role(){
        return "pages/basicSystem/role";
    }
    //角色管理--增加角色
    @RequestMapping(value="addRole")
    public String addRole(){
        return "pages/basicSystem/addRole";
    }
    //角色管理--编辑角色
    @RequestMapping(value="editRole")
    public String editRole(){
        return "pages/basicSystem/editRole";
    }
    //角色管理--删除角色

    //管理员管理
    @RequestMapping(value = "admin")
    public String admin(){
        return "pages/basicSystem/admin";
    }
    //管理员管理--增加管理员
    @RequestMapping(value = "addAdmin")
    public String addAdmin(){
        return "pages/basicSystem/addAdmin";
    }
    //管理员管理--编辑管理员
    @RequestMapping(value = "editAdmin")
    public String editAdmin(){
        return "pages/basicSystem/editAdmin";
    }
    //获取所有角色列表--已分页
    @ResponseBody
    @RequestMapping(value = "/getAllRole",method = RequestMethod.POST)
    public ResultBean<List<RoleEntity>> getAllRole(RoleEntity roleEntity){
        logger.info("====这是Controller的getAllRole");
        logger.info("前端传来的roleEntity："+roleEntity);
        return roleService.getAllRole(roleEntity);
    }
    //增加角色
    @ResponseBody
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public ResultBean<RoleEntity> addRole(@RequestBody RoleEntity roleEntity){
        logger.info("====这是Controller的addRole");
        logger.info("传过来的bean:"+roleEntity);
        ResultBean resultBean= roleService.addRole(roleEntity);
        System.out.println(resultBean.toString());
        return resultBean;
    }
    //编辑角色
    @ResponseBody
    @RequestMapping(value = "/editRole",method = RequestMethod.POST)
    public ResultBean<RoleEntity> editRole(@RequestBody RoleEntity roleEntity){
        logger.info("====这是controller的editAdmin");
        logger.info("传过来的bean:"+roleEntity);
        return roleService.editRole(roleEntity);
    }
    //删除角色
    @ResponseBody
    @RequestMapping(value = "/delRole",method = RequestMethod.POST)
    public ResultBean<AdminEntity> delRole(@RequestBody List<RoleEntity> roleEntityList){
        ResultBean resultBean= roleService.delRole(roleEntityList);
        return resultBean;
    }
    //获取所有管理员列表-已分页
    @ResponseBody
    @RequestMapping(value = "/getAllAdmin",method = RequestMethod.POST)
    @RequiresPermissions("sys:basicSystem_sysSetting")
    public ResultBean<List<AdminEntity>> getAllAdministrator(AdminEntity adminEntity){
        logger.info("page:"+adminEntity.getPage().toString());
        logger.info("pagesize:"+adminEntity.getLimit().toString());
        return adminService.getAllAdmin(adminEntity);
    }
    //增加管理员
    @ResponseBody
    @RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
    public ResultBean<AdminEntity> addAdmin(@RequestBody AdminEntity adminEntity){
        logger.info("传过来的bean:"+adminEntity);
        ResultBean resultBean= adminService.addAdmin(adminEntity);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(value = "/delAdmin",method = RequestMethod.POST)
    public ResultBean<AdminEntity> delAdmin(@RequestBody List<AdminEntity> adminEntityList){
        ResultBean resultBean= adminService.delAdmin(adminEntityList);
        return resultBean;
    }
    @ResponseBody
    @RequestMapping(value = "/queryAdmin",method = RequestMethod.POST)
    public ResultBean<List<AdminEntity>> queryAdmin(@RequestBody AdminEntity adminEntity){
        logger.info("传过来的bean:"+adminEntity);
       return adminService.queryAdmin(adminEntity);
    }
    @ResponseBody
    @RequestMapping(value = "/editAdmin",method = RequestMethod.POST)
    public ResultBean<AdminEntity> editAdmin(@RequestBody AdminEntity adminEntity){
        logger.info("====这是controller的editAdmin");
        logger.info("传过来的bean:"+adminEntity);
        return adminService.editAdmin(adminEntity);
    }

}
