package com.whut.truck.Dao;

import com.whut.truck.entity.SystemAdmin;

public interface SystemAdminDao {
    public SystemAdmin findByUsername(String username);
    public Integer save(SystemAdmin systemAdmin);
}
