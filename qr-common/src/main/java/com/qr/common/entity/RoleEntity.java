package com.qr.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * @Author: Jascon
 * @Description: 用户角色实体
 * @Date: 18:31 2020-01-20
 */
public class RoleEntity {
    private Integer id;
    private String roleKey;
    private String name;
    private String description;
    private String status;
    private String createTime;
    private String modifyTime;
    private Integer page;
    private Integer limit;

    private List<PermissionsEntity> permissions;//此角色拥有的权限集合

    public List<PermissionsEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionsEntity> permissions) {
        this.permissions = permissions;
    }

    @JsonIgnore
    public Integer getPage() {
        return page;
    }
    @JsonIgnore
    public Integer getLimit() {
        return limit;
    }
    @JsonProperty
    public void setPage(Integer page) {
        this.page = page;
    }
    @JsonProperty
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public RoleEntity() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


    public Integer getId() {
        return id;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", roleKey='" + roleKey + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                ", permissions=" + permissions +
                '}';
    }
}
