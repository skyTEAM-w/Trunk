package com.whut.truck.Dao;

import com.whut.truck.entity.SystemAdmin;

import java.io.IOException;

/**
 * @author yihaiqianxun
 */
public interface SystemAdminDao {
    public SystemAdmin findByUsername(String username) throws IOException;
    public Integer save(SystemAdmin systemAdmin) throws IOException;
}
