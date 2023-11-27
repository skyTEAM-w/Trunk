package org.whut.trunk.Service;

import org.whut.trunk.Dto.SystemAdminDto;
import org.whut.trunk.entity.SystemAdmin;

public interface SystemAdminService {
    public SystemAdminDto login(String username , String password);

    void save(SystemAdmin systemAdmin);
}
