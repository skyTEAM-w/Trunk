package com.whut.truck.Service;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.entity.SystemAdmin;

import java.io.IOException;

public interface SystemAdminService {
    //登录
    /**
     *
     * @param username 用户名
     * @param password  密码
     * @return SystemAdminDto 错误信息
     * @throws IOException
     */
    public SystemAdminDto login(String username , String password) throws IOException;
    //检查用户名是否已被注册
    /**
     *
     * @param username 用户名
     * @return SystemAdminDto错误信息
     * @throws IOException
     */
    SystemAdminDto check(String username) throws IOException;
    //注册
    /**
     *
     * @param systemAdmin SystemAdmin类
     * @return SystemAdminDto错误信息
     * @throws IOException
     */
    SystemAdminDto save(SystemAdmin systemAdmin) throws IOException;
    
}
