package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTOKhachHang;
import com.dutn.be_do_an_vat.dto.DTOTaiKhoan;
import com.dutn.be_do_an_vat.entity.Role;
import com.dutn.be_do_an_vat.entity.TaiKhoan;
import com.dutn.be_do_an_vat.entity.base_entity.E_Role;
import com.dutn.be_do_an_vat.repositoty.ITaiKhoan;
import com.dutn.be_do_an_vat.repositoty.iRole;
import com.dutn.be_do_an_vat.service.base_service.ITaiKhoanSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banking.utils.MapperUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
xứ lí login liên quan đến CRUD tài khoản
 */
@Service("taikhoanSer")
public class SerTaiKhoan implements ITaiKhoanSer {

    @Autowired
    private ITaiKhoan taiKhoanRes;

    @Autowired
    private iRole roleRes;

    @Override
    public DTOTaiKhoan themTaiKhoan(TaiKhoan taiKhoan) {
        Role role = roleRes.findByRole(E_Role.USER);
        taiKhoan.setRole(role);
        TaiKhoan taiKhoanSave = taiKhoanRes.save(taiKhoan);
        Map<String, String> permisions = new HashMap<>();
        role.getPermisions().forEach(i -> {
            permisions.put(i.getPermision().getMethod().name(), i.getUrl());
        });
        DTOTaiKhoan dtoTaiKhoan = DTOTaiKhoan
                .builder()
                .taiKhoan(taiKhoanSave.getTaiKhoan())
                .role(E_Role.USER.name())
                .permisions(permisions)
                .build();
        return dtoTaiKhoan;
    }

    @Override
    public DTOTaiKhoan suaTaiKhoan(TaiKhoan taiKhoan) {
        return null;
    }

    @Override
    public void xoaTaiKhoan(Long id) {

    }

    @Override
    public DTOTaiKhoan searchTaiKhoan(String name) {
        return null;
    }
}
