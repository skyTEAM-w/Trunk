package com.whut.truck.Dao;

import com.whut.truck.entity.SystemAdmin;

import java.io.IOException;

/**
 * @author yihaiqianxun
 */
public interface SystemAdminDao {
    //根据用户名查找用户数据
    /**
     *
     * @param username 用户名
     * @return SystemAdmin类
     * @throws IOException
     */
    public SystemAdmin findByUsername(String username) throws IOException;

    //注册信息
    /**
     *
     * @param systemAdmin SystemAdmin类
     * @return Integer错误信息
     * @throws IOException
     */
    public Integer save(SystemAdmin systemAdmin) throws IOException;
}
