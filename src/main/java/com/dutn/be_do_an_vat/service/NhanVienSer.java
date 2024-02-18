//package com.dutn.be_do_an_vat.service;
//
//import com.dutn.be_do_an_vat.dto.DTOTaiKhoan;
//import com.dutn.be_do_an_vat.entity.Role;
//import com.dutn.be_do_an_vat.entity.Role_Detail;
//import com.dutn.be_do_an_vat.entity.TaiKhoan;
//import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Role;
//import org.springframework.data.domain.PageRequest;
//
//import java.util.List;
//
//public class NhanVienSer {
//
//    @Override
//    public List getNhanVienBy(int soLuong, int trang) {
//
//        return resSanPham.findAll(PageRequest.of(trang, soLuong)).toList();
//
//    }
//    public DTOTaiKhoan themTaiKhoan(TaiKhoan taiKhoan) {
//        Role role = roleRes.findByRole(E_Role.ROLE_USER.name());
//        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoan.getMatKhau()));
//        TaiKhoan taiKhoanSave = taiKhoanRes.save(taiKhoan);
//
//        roleDetailRes.save(Role_Detail.builder()
//                .role(role)
//                .taiKhoan(taiKhoanSave)
//                .build()
//        );
//    }
//
//    }
//}
