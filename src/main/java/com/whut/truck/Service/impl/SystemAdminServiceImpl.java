package com.whut.truck.Service.impl;

import com.whut.truck.Dto.SystemAdminDto;
import com.whut.truck.Service.SystemAdminService;
import com.whut.truck.entity.SystemAdmin;
import com.whut.truck.Dao.SystemAdminDao;
import com.whut.truck.Dao.impl.SystemAdminDaoImpl;

import java.io.IOException;

public class SystemAdminServiceImpl implements SystemAdminService {
    private SystemAdminDao systemAdminDao = new SystemAdminDaoImpl();
    @Override
    public SystemAdminDto login(String username, String password) throws IOException {       //登录

        //1、通过username查询数据库//
        //2、查询结果为空，username错误
        //3、查询结果不为空，再判断password是否正确
        SystemAdmin systemAdmin = this.systemAdminDao.findByUsername(username);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if (systemAdmin == null) {
            systemAdminDto.setMsg(0);           //0表示用户名不存在
        } else {
            if (!systemAdmin.getPassword().equals(password)) {
                systemAdminDto.setMsg(1);            //1表示密码错误
            } else {
                systemAdminDto.setMsg(2);               //2表示登录成功
                systemAdminDto.setSystemAdmin(systemAdmin);
            }
        }
        return systemAdminDto;
    }

    @Override
    public SystemAdminDto check(String username) throws IOException {          //检查用户名是否已被注册
        SystemAdmin systemAdmin = this.systemAdminDao.findByUsername(username);
        SystemAdminDto systemAdminDto = new SystemAdminDto();
        if (systemAdmin == null) {
            systemAdminDto.setMsg(0);           //0表示用户名不存在
        } else {
            systemAdminDto.setMsg(3);               //3表示用户名已被注册
            systemAdminDto.setSystemAdmin(systemAdmin);

        }
        return systemAdminDto;
    }

    @Override
    public SystemAdminDto save(SystemAdmin systemAdmin) throws IOException {        //注册
        Integer save = this.systemAdminDao.save(systemAdmin);
        if(save != 1) throw new RuntimeException("注册信息添加失败");
        return null;
    }
}
