package com.ok.dvweb.util;

import java.util.Arrays;
import java.util.List;


public enum Role {

    COSTUMER(List.of(Permission.ONLY_READ)),
    ADMIN(List.of(Permission.ONLY_READ, Permission.WRITE, Permission.REWRITE));

    private List<Permission> permissionList;

    Role(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
