package com.whut.truck.Service.impl;

import com.whut.truck.Dao.SystemAdminDao;
import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Service.SystemAdminService;
import com.whut.truck.entity.SystemAdmin;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {
    
    private final SystemAdminDao systemAdminDao;

    public SystemAdminServiceImpl(SystemAdminDao systemAdminDao) {
        this.systemAdminDao = systemAdminDao;
    }

    @Override
    public SystemAdminDto login(String username, String password) throws IOException {
        SystemAdmin systemAdmin = this.systemAdminDao.findByUsername(username);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if (systemAdmin == null) {
            systemAdminDto.setMsg(0);
        } else {
            if (!systemAdmin.getPassword().equals(password)) {
                systemAdminDto.setMsg(1);
            } else {
                systemAdminDto.setMsg(2);
                systemAdminDto.setSystemAdmin(systemAdmin);
            }
        }
        return systemAdminDto;
    }

    @Override
    public SystemAdminDto check(String username) throws IOException {
        SystemAdmin systemAdmin = this.systemAdminDao.findByUsername(username);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if (systemAdmin == null) {
            systemAdminDto.setMsg(0);
        } else {
            systemAdminDto.setMsg(3);
            systemAdminDto.setSystemAdmin(systemAdmin);
        }
        return systemAdminDto;
    }

    @Override
    public SystemAdminDto save(SystemAdmin systemAdmin) throws IOException {
        Integer save = this.systemAdminDao.save(systemAdmin);
        if (save != 1) throw new RuntimeException("注册信息添加失败");
        return null;
    }
}
