package com.whut.truck.Dto;

import com.whut.truck.entity.SystemAdmin;

public class SystemAdminDto {
    //错误信息封装
    private Integer msg;
    // 用户对象
    private SystemAdmin SystemAdmin;

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public com.whut.truck.entity.SystemAdmin getSystemAdmin() {
        return SystemAdmin;
    }

    public void setSystemAdmin(com.whut.truck.entity.SystemAdmin systemAdmin) {
        SystemAdmin = systemAdmin;
    }
}
