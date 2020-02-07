package com.qr.admin.mapper;

import com.qr.common.bean.ResultBean;
import com.qr.common.entity.AdminEntity;
import com.qr.common.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jascon
 * @Description:
 * @Date: 18:49 2020-01-20
 */
@Repository
public interface RoleMapper {
    RoleEntity getRole(int id);
    List<RoleEntity> getAllRole();
    RoleEntity getRoleById(AdminEntity adminEntity);
    int addRole(RoleEntity roleEntity);
    int delRole(List<RoleEntity> roleEntityList);
    int editRole(RoleEntity roleEntity);
    int resetUserRole(List<RoleEntity> roleEntityList);
}
