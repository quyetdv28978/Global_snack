package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTOTaiKhoan;
import com.dutn.be_do_an_vat.entity.*;
import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Role;
import com.dutn.be_do_an_vat.repositoty.IKhachHang;
import com.dutn.be_do_an_vat.repositoty.IRoleDetail;
import com.dutn.be_do_an_vat.repositoty.ITaiKhoan;
import com.dutn.be_do_an_vat.repositoty.iRole;
import com.dutn.be_do_an_vat.service.base_service.ITaiKhoanSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/*
xứ lí login liên quan đến CRUD tài khoản
 */
@Service("taikhoanSer")
public class SerTaiKhoan implements ITaiKhoanSer {

    @Autowired
    private ITaiKhoan taiKhoanRes;
    @Autowired
    private IKhachHang khachHang;
    @Autowired
    private iRole roleRes;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRoleDetail roleDetailRes;

    @Override
    public DTOTaiKhoan themTaiKhoan(TaiKhoan taiKhoan) {
        Role role = roleRes.findByRole(E_Role.ROLE_USER.name());
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoan.getMatKhau()));
        TaiKhoan taiKhoanSave = taiKhoanRes.save(taiKhoan);
        KhachHang kh = new KhachHang();
        kh.setName(taiKhoan.getTaiKhoan());
        kh.setTaiKhoan(taiKhoanSave);
        kh.setGioHang(GioHang.builder()
                .ngayTao(LocalDate.now())
                .trangThai(0)
                .build());
        khachHang.save(kh);
        roleDetailRes.save(Role_Detail.builder()
                .role(role)
                .taiKhoan(taiKhoanSave)
                .build()
        );

        Map<String, String> permisions = new HashMap<>();
        role.getPermisions().stream().forEach(i -> {
            permisions.put(i.getPermision().getMethod().name(), i.getPermision().getUrl());
        });

        DTOTaiKhoan dtoTaiKhoan = DTOTaiKhoan
                .builder()
                .taiKhoan(taiKhoanSave.getTaiKhoan())
                .role(E_Role.ROLE_USER.name())
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
