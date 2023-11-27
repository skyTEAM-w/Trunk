package org.whut.trunk.Dao;

import org.whut.trunk.entity.SystemAdmin;

public interface SystemAdminDao {
    public SystemAdmin findByUsername(String username);
    public Integer save(SystemAdmin systemAdmin);
}
