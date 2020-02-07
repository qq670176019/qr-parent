package com.qr.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author:Jascon
 * @date:2020-01-23 18:23
 * 管理员类
 */
public class AdminEntity extends UserEntity{
    private Integer canDelete;//是否能被删除
    private String verCode;
    private Integer roleId;
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 重写父类设置角色方法，同时写入子类的角色id和角色名称中
     * @param roleEntity
     */
    @Override
    public void setRoleEntity(RoleEntity roleEntity) {
        super.setRoleEntity(roleEntity);
        this.roleId=roleEntity.getId();
        this.roleName=roleEntity.getName();
    }

    @JsonIgnore
    public String getVerCode() {
        return verCode;
    }

    @JsonProperty
    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public AdminEntity() {
    }

    public Integer getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Integer canDelete) {
        this.canDelete = canDelete;
    }

    @Override
    public String toString() {
        return super.toString()+"\nAdminEntity{" +
                "canDelete=" + canDelete +
                ", verCode='" + verCode + '\'' +
                ", roleId=" + roleId +
                ", roleName=" + roleName +
                '}';
    }
}
