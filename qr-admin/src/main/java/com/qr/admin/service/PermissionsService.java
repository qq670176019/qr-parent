package com.qr.admin.service;

import com.qr.admin.mapper.PermissionsMapper;
import com.qr.common.entity.PermissionsEntity;
import com.qr.common.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:Jascon
 * @date:2020-02-03 19:00
 */
@Service
public class PermissionsService {
    @Autowired
    private PermissionsMapper pMapper;

    public List<PermissionsEntity> getAllPermissionsByRoleId(RoleEntity roleEntity){
        return pMapper.getAllPermissionsByRoleId(roleEntity);
    }
}
