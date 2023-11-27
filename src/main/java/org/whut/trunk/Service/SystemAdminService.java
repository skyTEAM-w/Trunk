package org.whut.trunk.Service;

import org.whut.trunk.Dto.SystemAdminDto;

public interface SystemAdminService {
    public SystemAdminDto login(String username , String password);
}
