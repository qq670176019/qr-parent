package com.qr.admin.mapper;

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
public interface AdminMapper {
    int addAdmin(AdminEntity adminEntity);//插入User中
    int addAdminUser(AdminEntity adminEntity);//插入admin_user中
    int addUserRole(AdminEntity adminEntity);//插入user_role中
    int delAdmin(List<AdminEntity> adminEntityList);
    int editAdmin(AdminEntity adminEntity);
    int editUserRole(AdminEntity adminEntity);
    List<AdminEntity> getAllAdmin();
    List<AdminEntity> queryAdmin(AdminEntity adminEntity);
    List<AdminEntity> getAdminUserListByRoleId(RoleEntity roleEntity);
    AdminEntity getAdminByAccountName(AdminEntity adminEntity);
    AdminEntity getAdminByUsername(AdminEntity adminEntity);

}
