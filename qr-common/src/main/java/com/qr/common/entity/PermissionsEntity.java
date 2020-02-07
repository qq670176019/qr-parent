package com.qr.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author:Jascon
 * @date:2020-02-03 13:58
 */
public class PermissionsEntity {
    private Integer id;
    private String name;
    private Integer parentId;
    private String perKey;
    private Integer type;
    private String perUrl;
    private Integer order;
    private String permission;
    private String description;
    private String createTime;
    private String modifyTime;
    private String icon;

    private Integer page;
    private Integer limit;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getPerKey() {
        return perKey;
    }

    public Integer getType() {
        return type;
    }

    public String getPerUrl() {
        return perUrl;
    }

    public Integer getOrder() {
        return order;
    }

    public String getPermission() {
        return permission;
    }

    public String getDescription() {
        return description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }
    @JsonIgnore
    public Integer getPage() {
        return page;
    }
    @JsonIgnore
    public Integer getLimit() {
        return limit;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setPerKey(String perKey) {
        this.perKey = perKey;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setPerUrl(String perUrl) {
        this.perUrl = perUrl;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    @JsonProperty
    public void setPage(Integer page) {
        this.page = page;
    }
    @JsonProperty
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", perKey='" + perKey + '\'' +
                ", type=" + type +
                ", perUrl='" + perUrl + '\'' +
                ", order=" + order +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                ", icon=" + icon +
                '}';
    }

    public PermissionsEntity() {
    }
}
