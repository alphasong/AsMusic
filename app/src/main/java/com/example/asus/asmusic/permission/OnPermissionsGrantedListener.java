package com.example.asus.asmusic.permission;

import java.util.List;

/**
 * @desciption: 接收权限
 */

public interface OnPermissionsGrantedListener {
    void onPermissionsGranted(PermissionBuilder builder, List<String> perms);
}
