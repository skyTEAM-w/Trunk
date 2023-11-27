package org.whut.trunk.Dto;

import org.whut.trunk.entity.SystemAdmin;

public class SystemAdminDto {
    //错误信息封装
    private Integer msg;
    private SystemAdmin SystemAdmin;

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public org.whut.trunk.entity.SystemAdmin getSystemAdmin() {
        return SystemAdmin;
    }

    public void setSystemAdmin(org.whut.trunk.entity.SystemAdmin systemAdmin) {
        SystemAdmin = systemAdmin;
    }
}
