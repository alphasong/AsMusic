package com.example.asus.asmusic.permission;

import java.util.List;

/**
 * @desciption: 拒绝权限
 */

public interface OnPermissionsDeniedListener {
    void onPermissionsDenied(PermissionBuilder builder, List<String> perms);
}
