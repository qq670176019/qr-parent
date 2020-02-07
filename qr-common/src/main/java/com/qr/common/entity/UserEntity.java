package com.qr.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * 所有用户的父类
 * 管理员、渠道、发行商、游戏玩家的父类
 */
public class UserEntity {
    private Integer page;
    private Integer limit;
    private Integer id;
    private String name;//名字、昵称
    private String username;//账户名、用户名
    private String password;
    @JsonIgnore
    private String salt;
    private String locked;
    private String createTime;
    private String modifyTime;
    private String status;
    private String mobile;
    private String email;

    private RoleEntity roleEntity;

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public UserEntity() {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }



    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }



    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    /**
     * 加此注解在getter上可防止将此字段数据传至前端
     * 需要在对应setter上加注解，允许前端设置数据
     * @return
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }


    public String getCreateTime() {
        return createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }


    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getLocked() {
        return locked;
    }

    public String getStatus() {
        return status;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "page=" + page +
                ", limit=" + limit +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", locked='" + locked + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", status='" + status + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", roleEntity=" + roleEntity +
                '}';
    }
}
