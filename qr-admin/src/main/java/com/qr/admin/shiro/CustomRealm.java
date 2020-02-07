package com.qr.admin.shiro;

import com.qr.admin.service.AdminService;
import com.qr.admin.service.HomeService;
import com.qr.common.entity.AdminEntity;
import com.qr.common.entity.PermissionsEntity;
import com.qr.common.entity.RoleEntity;
import com.qr.common.exceptions.CustomAuthenticationException;
import com.qr.common.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:Jascon
 * @date:2020-02-03 15:48
 * 自定义的shiro域：Shiro 从从 Realm 获取安全数据（如用户、角色、权限），安全数据源
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

     /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名去数据库查询用户信息
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsername(name);

        adminEntity = adminService.getAdminByUsername(adminEntity);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        RoleEntity tRole = adminEntity.getRoleEntity();
        simpleAuthorizationInfo.addRole(tRole.getName());//添加角色
        List<PermissionsEntity> pList = adminEntity.getRoleEntity().getPermissions();
        //把权限转出来
        for (PermissionsEntity p : pList) {
            simpleAuthorizationInfo.addStringPermission(p.getPermission());
        }
        System.out.println("输出一下用户： " + adminEntity.toString());
        //simpleAuthorizationInfo.addStringPermissions(perList);//把权限填入

        /**
         for (Role role : user.getRoles()) {
         //添加角色
         simpleAuthorizationInfo.addRole(role.getRoleName());
         //添加权限
         for (Permissions permissions : role.getPermissions()) {
         simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
         }
         }*/
        return simpleAuthorizationInfo;
    }

    /**
     * 验证身份
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        //加这一步的目的是在post请求时会先进入认证然后再到请求。
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取登录时输入的用户名和密码
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        AdminEntity user = new AdminEntity();//登陆时，用户传来的
        log.info("user name:" + name);
        log.info("user password:" + password);
        user.setUsername(name);
        user.setPassword(password);
        // 从数据库获取对应用户名密码的用户
        AdminEntity queryEntity = adminService.getAdminByUsername(user);
        System.out.println("queryEntity: " + queryEntity);
        if (queryEntity == null) {//如果查不到用户
            throw new UnknownAccountException();
        } else if (queryEntity.getLocked().equals("1")) {//检查是否被锁定
            throw new CustomAuthenticationException(1, CustomAuthenticationException.UserLocked);
        } else if (queryEntity.getStatus().equals("1")) {//检查是否被禁用
            throw new CustomAuthenticationException(1, CustomAuthenticationException.UserDisabled);
        } else {//验证登陆用户密码是否正确
            String MD5SaltPwd = PasswordUtils.getSaltMD5(PasswordUtils.md5Hex(user.getPassword()), queryEntity.getSalt());//获取加盐密码
            if (!queryEntity.getPassword().equals(MD5SaltPwd)) {
                throw new IncorrectCredentialsException();
            } else {//检查通过
                log.info("---------------- Shiro 凭证认证成功 ----------------------");
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                        queryEntity.getUsername(), //用户
                        queryEntity.getPassword(), //密码
                        getName()//realm name
                );
                Object o=authenticationInfo.getCredentials();//凭据
                System.out.println("o is :"+o.toString());
                return authenticationInfo;
            }
        }

    }

}

