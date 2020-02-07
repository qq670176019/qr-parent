package com.qr.common.entity;

/**
 * @author:Jascon
 * @date:2020-02-03 22:11
 */
public class ChannelEntity extends UserEntity {
    private String parentId;//上级渠道ID
    private String parentName;//上级渠道名称
    private String qq;
    private Integer isSchargeOpen;//是否开启代充

    public ChannelEntity() {
    }

    @Override
    public String toString() {
        return super.toString()+"\nChannelEntity{" +
                ", parentChannelId='" + parentId + '\'' +
                ", parentChannelName='" + parentName + '\'' +
                ", qq='" + qq + '\'' +
                ", isSchargeOpen=" + isSchargeOpen +
                '}';
    }
    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setIsSchargeOpen(Integer isSchargeOpen) {
        this.isSchargeOpen = isSchargeOpen;
    }


    public String getParentId() {
        return parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getQq() {
        return qq;
    }

    public Integer getIsSchargeOpen() {
        return isSchargeOpen;
    }
}
