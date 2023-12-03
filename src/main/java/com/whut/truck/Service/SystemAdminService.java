package com.whut.truck.Service;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.entity.SystemAdmin;

import java.io.IOException;

public interface SystemAdminService {
    public SystemAdminDto login(String username , String password) throws IOException;

    SystemAdminDto check(String username) throws IOException;
    SystemAdminDto save(SystemAdmin systemAdmin) throws IOException;
    
}
