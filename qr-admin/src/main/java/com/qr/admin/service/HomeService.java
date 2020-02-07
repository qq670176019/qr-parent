package com.qr.admin.service;

import com.qr.admin.mapper.AdminMapper;
import com.qr.admin.mapper.PermissionsMapper;
import com.qr.admin.mapper.RoleMapper;
import com.qr.common.bean.ResultBean;
import com.qr.common.entity.AdminEntity;
import com.qr.common.entity.LoginInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:Jascon
 * @date:2020-02-06 16:17
 */
@Service
@Slf4j
public class HomeService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionsMapper perMapper;
    public ResultBean<LoginInfoEntity> doLogin(AdminEntity adminEntity) throws Exception {
        //1、验证前台传来的用户信息
        //2、查询出用户的角色、权限信息
        //3、添加用户认证信息

        //验证之后传回来的正确信息，如果不存在则直接抛异常
        adminEntity=adminMapper.getAdminByUsername(adminEntity);
        //查询出此用户的角色
        adminEntity.setRoleEntity(roleMapper.getRoleById(adminEntity));
        //查询出此用户的角色对应的权限列表
        adminEntity.getRoleEntity().setPermissions(perMapper.getAllPermissionsByRoleId(adminEntity.getRoleEntity()));
        //生成accessToken


        //adminEntity.setRoles(roleMapper.getRoleById(adminEntity));//设置权限(roleId和RoleName一块设置了)
        //adminEntity.getRoles()
        //ResultBean result=new ResultBean();
        // return result;
        ResultBean<LoginInfoEntity> resultBean=new ResultBean<>();
        LoginInfoEntity l=new LoginInfoEntity();
        l.setAccessToken("1233211234567");
        resultBean.setData(l);
        return resultBean;
    }
}
