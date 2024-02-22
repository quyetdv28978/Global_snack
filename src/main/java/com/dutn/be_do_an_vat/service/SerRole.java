package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.*;
import com.dutn.be_do_an_vat.entity.*;
import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Permision_Method;
import com.dutn.be_do_an_vat.exception.RoleNotFounndException;
import com.dutn.be_do_an_vat.repositoty.*;
import com.dutn.be_do_an_vat.service.base_service.IRoleSer;
import com.dutn.be_do_an_vat.utility.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SerRole implements IRoleSer {
    @Autowired
    private iRole roleRes;

    @Autowired
    private ITaiKhoan taiKhoanRes;

    @Autowired
    private IPermision permisionRes;

    @Autowired
    private IPermisionDetail permisionDetailRes;

    @Autowired
    private IRoleDetail roleDetailRes;

    @Override
    @Transactional
    public DTORole addRole(DTORole dtoRole) {
        Role role = roleRes.save(Role.builder()
                .role(dtoRole.getRole())
                .build());

        dtoRole.getDtoPermision().forEach(i -> {
            permisionDetailRes.save(PermisionDetail.builder()
                    .role(role)
                    .permision(permisionRes.save(Permision.builder()
                            .method(E_Permision_Method.valueOf(i.getMethod()))
                            .namePermision(i.getPermision())
                            .url(i.getUrl())
                            .build()))
                    .build());
        });
        return dtoRole;
    }

    @Override
    @Transactional
    public Role updateRole(DTOUpdateROle dtoRole) {
        Role role = roleRes.findById(dtoRole.getIdRole()).orElseThrow(() -> new RoleNotFounndException(Const.ROLE_NOT_FOUND));
        role.setRole(dtoRole.getRole());
        role.setTrangThai(dtoRole.getTrangThai());
        permisionDetailRes.deleteAllById(() -> role.getPermisions().stream().map(i -> i.getId()).collect(Collectors.toList()).iterator());

        Set a = dtoRole.getIdRoles().stream().map(i -> {
            return permisionDetailRes.saveAndFlush(PermisionDetail.builder()
                    .role(role)
                    .permision(permisionRes.findById(Long.valueOf(i)).get())
                    .build()
            );
        }).collect(Collectors.toSet());

        role.setPermisions(a);
        role.getPermisions().forEach(System.out::println);
        return roleRes.findById(dtoRole.getIdRole()).get();
    }

    @Override
    public TaiKhoan updateRoleUser(Long idUser, List<Integer> idRole) {
        TaiKhoan taiKhoan = taiKhoanRes.findById(idUser)
                .orElseThrow(() -> new UsernameNotFoundException(Const.USER_NOT_FOUND));
        roleDetailRes.deleteAllById(() -> taiKhoan.getRoleDetails().stream().map(i -> i.getId()).collect(Collectors.toList()).iterator());

        idRole.stream().map(i -> roleRes.findById(Long.valueOf(i)).get()).collect(Collectors.toSet())
                .forEach(i -> {
                    roleDetailRes.save(Role_Detail.builder()
                            .role(i)
                            .taiKhoan(taiKhoan)
                            .build()
                    );
                });

        return taiKhoan;
    }

    @Override
    public void deleteRole(Long idRole) {
        roleRes.deleteById(idRole);
    }
}
