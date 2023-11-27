package org.whut.trunk.Service.impl;

import org.whut.trunk.Dao.SystemAdminDao;
import org.whut.trunk.Dao.impl.SystemAdminDaoImpl;
import  org.whut.trunk.Dto.SystemAdminDto;
import  org.whut.trunk.Service.SystemAdminService;
import org.whut.trunk.entity.SystemAdmin;

public class SystemAdminServiceImpl implements SystemAdminService{
    private SystemAdminDao systemAdminDao = new SystemAdminDaoImpl();
    @Override
    public SystemAdminDto login(String username, String password) {

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
    public void save(SystemAdmin systemAdmin){
        Integer save = this.systemAdminDao.save(systemAdmin);
        if(save != 1) throw new RuntimeException("注册信息添加失败");
    }
}
