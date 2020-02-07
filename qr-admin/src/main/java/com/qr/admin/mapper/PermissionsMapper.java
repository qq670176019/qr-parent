package com.qr.admin.mapper;

import com.qr.common.entity.PermissionsEntity;
import com.qr.common.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:Jascon
 * @date:2020-02-03 18:57
 */
@Repository
public interface PermissionsMapper {
    List<PermissionsEntity> getAllPermissionsByRoleId(RoleEntity roleEntity);
    PermissionsEntity getAllPermissionsById(PermissionsEntity permissions);
}
