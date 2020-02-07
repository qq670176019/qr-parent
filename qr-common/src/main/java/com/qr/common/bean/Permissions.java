package com.qr.common.bean;

/**
 * @author:Jascon
 * @date:2020-02-03 16:01
 */
public class Permissions {
    private String id;
    private String permissionsName;

    public String getId() {
        return id;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "id='" + id + '\'' +
                ", permissionsName='" + permissionsName + '\'' +
                '}';
    }

    public Permissions(String id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}
