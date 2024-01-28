package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.dto.DTOPermision;
import com.dutn.be_do_an_vat.dto.DTORole;
import com.dutn.be_do_an_vat.dto.DTOUpdateROle;
import com.dutn.be_do_an_vat.dto.DTOUpdateRoleUser;
import com.dutn.be_do_an_vat.entity.Role;
import com.dutn.be_do_an_vat.entity.TaiKhoan;

import java.util.List;
import java.util.Set;

public interface IRoleSer {
    DTORole addRole(DTORole dtoRole);

    Role updateRole(DTOUpdateROle dtoRole);

    TaiKhoan updateRoleUser(Long idUser, List<Integer> idRole);

    void deleteRole(Long idRole);
}
