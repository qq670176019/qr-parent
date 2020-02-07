package com.qr.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qr.admin.mapper.AdminMapper;
import com.qr.admin.mapper.RoleMapper;
import com.qr.common.bean.Permissions;
import com.qr.common.bean.ResultBean;
import com.qr.common.entity.AdminEntity;
import com.qr.common.entity.RoleEntity;
import com.qr.common.utils.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Jascon
 * @Description:
 * @Date: 18:48 2020-01-20
 */
@Service
@Slf4j
public class AdminService {
    @Autowired
    AdminMapper adMapper;
    @Autowired
    RoleMapper roleMapper;


    /**
     * 获取所有管理员列表
     *
     * @return
     */
    public ResultBean<List<AdminEntity>> getAllAdmin(AdminEntity adminEntity) {
        log.info("getAllAdmin--前端传过来的bean:" + adminEntity.toString());
        //使用PageHelper获取分页和分页count
        PageHelper.startPage(adminEntity.getPage(), adminEntity.getLimit());//页码，每页数量
        List<AdminEntity> adList = adMapper.getAllAdmin();//通过mybatis获取所有结果
        //为每个admin查询出其所属角色
        for (AdminEntity ad:adList) {
            ad.setRoleEntity(roleMapper.getRoleById(ad));
        }
        PageInfo<AdminEntity> pageInfo = new PageInfo<>(adList);//将所有结果给pageinfo
        //将结果集封装到页面bean中
        ResultBean<List<AdminEntity>> administratorEntityResultBeanList = new ResultBean<>(pageInfo.getList(), (int) pageInfo.getTotal());
        return administratorEntityResultBeanList;
    }

    /**
     * 查询某个管理员
     *
     * @return
     */
    public ResultBean<List<AdminEntity>> queryAdmin(AdminEntity adminEntity) {
        log.info("queryAdmin--前端传过来的bean:" + adminEntity.toString());
        //使用PageHelper获取分页和分页count
        PageHelper.startPage(adminEntity.getPage(), adminEntity.getLimit());//页码，每页数量
        List<AdminEntity> adList = adMapper.queryAdmin(adminEntity);//通过mybatis获取所有结果
        PageInfo<AdminEntity> pageInfo = new PageInfo<>(adList);//将所有结果给pageinfo
        List resultList = pageInfo.getList();
        System.out.println("后台查询到的数据" + resultList.toString());
        //将结果集封装到页面bean中
        ResultBean<List<AdminEntity>> administratorEntityResultBeanList = new ResultBean<>(resultList, resultList.size());
        System.out.println("resultBean: " + administratorEntityResultBeanList);
        return administratorEntityResultBeanList;
    }

    /**
     * 新增管理员
     *
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 36000, rollbackFor = Exception.class)
    public ResultBean addAdmin(AdminEntity adminEntity) {
        log.info("addAdmin--前端传过来的bean:" + adminEntity.toString());
        adminEntity.setSalt(PasswordUtils.getRandomSalt());//获取salt
        String MD5SaltPwd = PasswordUtils.getSaltMD5(PasswordUtils.md5Hex(adminEntity.getPassword()), adminEntity.getSalt());//获取加盐密码
        adminEntity.setPassword(MD5SaltPwd);
        if (adminEntity.getStatus() != null && adminEntity.getStatus().equals("on")) {//转换switch状态
            adminEntity.setStatus("0");
        } else if (adminEntity.getStatus() == null) {
            adminEntity.setStatus("1");
        }
        if (adminEntity.getLocked() != null && adminEntity.getLocked().equals("on")) {//转换switch状态
            adminEntity.setLocked("0");
        } else if (adminEntity.getLocked() == null) {
            adminEntity.setLocked("1");
        }
        int result1 = adMapper.addAdmin(adminEntity);//在user表中插入数据
        //System.out.println("插入user之后的adminEntity数据： "+adminEntity);
        int result2 = adMapper.addAdminUser(adminEntity);//在admin_user表中插入数据
        int result3=adMapper.addUserRole(adminEntity);
        //System.out.println("插入admin_user结果： "+result2);
        RoleEntity rr=roleMapper.getRoleById(adminEntity);
        //System.out.println("查询出来的roleEntity： "+);
        adminEntity.setRoleEntity(rr);
        //int result4 = adMapper.addUserRole(adminEntity);//操作user_role,设置角色，这个地方出了问题
        ResultBean rb = new ResultBean();
        if (!(result1 == 1 && result2 == 1)) {
            rb.setCode(1);
            rb.setMsg("系统内部错误，请联系管理员!");
        }
        return rb;
    }

    /**
     * 删除管理员
     *
     * @param adminEntityList
     * @return
     */
    public ResultBean<AdminEntity> delAdmin(List<AdminEntity> adminEntityList) {
        log.info("delAdmin--前端传过来的bean:" + adminEntityList.toString());
        ResultBean rb = new ResultBean();

        for (AdminEntity ad : adminEntityList) {//如果list中有管理员，则不允许被删除，直接返回
            if (ad.getId().equals("1")) {
                rb.setCode(1);
                rb.setMsg("超级管理员不可删除！");
                return rb;
            }
        }
        //正常执行以下代码
        int result = adMapper.delAdmin(adminEntityList);
        int count = adminEntityList.size();
        if (result != count) {
            rb.setCode(1);
            rb.setMsg("部分操作未执行成功，请联系管理员!");
        }
        return rb;
    }

    /**
     * 编辑管理员
     *
     * @param adminEntity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 36000, rollbackFor = Exception.class)
    public ResultBean<AdminEntity> editAdmin(AdminEntity adminEntity) {
        //前台不允许修改accountName，是否还需要在后台约束一下？
        log.info("editAdmin--前端传过来的bean:" + adminEntity.toString());
        ResultBean rb = new ResultBean();
        if (adminEntity.getId() == 1) {//超级管理员不可以编辑账号、名称、状态
            adminEntity.setUsername("");
            adminEntity.setName("");
        }
        if (adminEntity.getStatus() != null && adminEntity.getStatus().equals("on")) {//转换switch状态
            adminEntity.setStatus("0");
        } else if (adminEntity.getStatus() == null) {
            adminEntity.setStatus("1");
        }
        if (adminEntity.getLocked() != null && adminEntity.getLocked().equals("on")) {//转换switch状态
            adminEntity.setLocked("0");
        } else if (adminEntity.getLocked() == null) {
            adminEntity.setLocked("1");
        }
        if (adminEntity.getPassword() != null) {//如果要修改密码
            adminEntity.setSalt(PasswordUtils.getRandomSalt());//获取salt
            String MD5SaltPwd = PasswordUtils.getSaltMD5(PasswordUtils.md5Hex(adminEntity.getPassword()), adminEntity.getSalt());//获取加盐密码
            adminEntity.setPassword(MD5SaltPwd);
        }
        int result = adMapper.editAdmin(adminEntity);//修改user表
        int result2 = adMapper.editUserRole(adminEntity);//修改user_role表

        if (result != 1) {
            rb.setCode(1);
            rb.setMsg("部分操作未执行成功，请联系管理员!");
        }
        return rb;
    }

    /**
     * 获取单个管理员--登录使用
     *
     * @param adminEntity
     * @return
     */
    public ResultBean<AdminEntity> getAdminByAccountName(AdminEntity adminEntity) {
        ResultBean<AdminEntity> result = new ResultBean<>();
        AdminEntity ad2 = adMapper.getAdminByAccountName(adminEntity);
        return result;
    }

    /**
     * 登录
     *
     * @param adminEntity
     * @return
     */
    public ResultBean<AdminEntity> login(AdminEntity adminEntity) {
        //先验证是否有此账户
        //然后查出此账户所有权限和所属角色
        //返回给controller
        return null;
    }

    /**
     * 获取用户所属角色
     *
     * @param adminEntity
     * @return
     */
    private List<RoleEntity> getRoles(AdminEntity adminEntity) {
        return null;
    }

    /**
     * 获取某角色的权限集合
     *
     * @return
     */
    private List<Permissions> getPermissions(RoleEntity roleEntity) {
        return null;
    }

    /**
     * 登录使用，根据用户名和密码查询相关用户
     *
     * @param adminEntity
     * @return
     */
    public AdminEntity getAdminByUsername(AdminEntity adminEntity) {
        AdminEntity queryEntity = adMapper.getAdminByUsername(adminEntity);


        return queryEntity;
    }

}
