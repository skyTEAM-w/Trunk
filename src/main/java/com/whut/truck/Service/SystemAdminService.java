package com.whut.truck.Service;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.entity.SystemAdmin;

public interface SystemAdminService {
    public SystemAdminDto login(String username , String password);

    void save(SystemAdmin systemAdmin);
}
