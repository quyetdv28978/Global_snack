package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.DTONhanVien;
import com.dutn.be_do_an_vat.entity.*;
import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Role;
import com.dutn.be_do_an_vat.exception.KhachHangException;
import com.dutn.be_do_an_vat.repositoty.INhanVien;
import com.dutn.be_do_an_vat.repositoty.IRoleDetail;
import com.dutn.be_do_an_vat.repositoty.ITaiKhoan;
import com.dutn.be_do_an_vat.repositoty.iRole;
import com.dutn.be_do_an_vat.service.base_service.IService;
import com.dutn.be_do_an_vat.utility.Const;
import com.dutn.be_do_an_vat.utility.MapperUtils;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NhanVienSer implements IService<DTONhanVien> {
    @Autowired
    private INhanVien nhanVienRes;
    @Autowired
    private ITaiKhoan taiKhoanRes;
    @Autowired
    private IRoleDetail roleDetailRes;
    @Autowired
    private iRole roleRes;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Optional add(DTONhanVien dtoNhanVien) {
        Role role = roleRes.findByRole(E_Role.ROLE_USER.name());
        dtoNhanVien.setMatKhau(passwordEncoder.encode(dtoNhanVien.getMatKhau()));
        TaiKhoan taiKhoanSave = taiKhoanRes.save(TaiKhoan.builder()
                .taiKhoan(dtoNhanVien.getTaiKhoan())
                .matKhau(dtoNhanVien.getMatKhau())
                .build());
        roleDetailRes.save(Role_Detail
                .builder()
                .role(role)
                .taiKhoan(taiKhoanSave)
                .build());

        NhanVien nhanVien = MapperUtils.dtoToEntity(dtoNhanVien, NhanVien.class);
        nhanVien.setTaiKhoan(taiKhoanSave);
        nhanVienRes.save(nhanVien);

        dtoNhanVien.setMatKhau("");
        return Optional.of(dtoNhanVien);
    }

    @Override
    public Optional update(Long id, DTONhanVien dtoNhanVien) {
        if (!nhanVienRes.findById(id).isPresent()) throw new KhachHangException(Const.KH_NOT_FOUND);
        dtoNhanVien.setMatKhau(passwordEncoder.encode(dtoNhanVien.getMatKhau()));
        TaiKhoan taiKhoanSave = taiKhoanRes.findUserByNameNhanVien(id);
        taiKhoanSave.setMatKhau(dtoNhanVien.getMatKhau());
        taiKhoanSave.setTaiKhoan(dtoNhanVien.getTaiKhoan());
        taiKhoanRes.save(taiKhoanSave);
        NhanVien nhanVien = MapperUtils.dtoToEntity(dtoNhanVien, NhanVien.class);
        nhanVien.setName(dtoNhanVien.getFullName());
        nhanVien.setId(id);
        nhanVien.setDOB(dtoNhanVien.getDOB());
        System.out.println(nhanVien.getFullName());
        nhanVienRes.save(nhanVien);

        dtoNhanVien.setMatKhau("");
        return Optional.of(dtoNhanVien);
    }

    @Override
    public void delete(Long id) {
        if (!nhanVienRes.findById(id).isPresent()) throw new KhachHangException(Const.KH_NOT_FOUND);
        nhanVienRes.deleteById(id);
    }

    @Override
    public DTONhanVien search(Long id) {
        if (!nhanVienRes.findById(id).isPresent()) throw new KhachHangException(Const.KH_NOT_FOUND);
        NhanVien nhanVien = nhanVienRes.findById(id).get();
        DTONhanVien nhanVienDTO = new DTONhanVien();
        nhanVienDTO.setMatKhau(nhanVien.getTaiKhoan().getMatKhau());
        nhanVienDTO.setTaiKhoan(nhanVien.getTaiKhoan().getTaiKhoan());
        nhanVienDTO.setId(id);
        nhanVienDTO.setDOB(nhanVienDTO.getDOB());
        nhanVienDTO.setGioiTinh(nhanVien.getGioiTinh());
        nhanVienDTO.setFullName(nhanVien.getFullName());
        return nhanVienDTO;
    }

    @Override
    public List getAll_filter(int soLuong, int trang) {
        return nhanVienRes.findAll(PageRequest.of(trang, soLuong)).stream().map(i -> DTONhanVien.builder()
                .DOB(i.getDOB())
                .fullName(i.getName())
                .gioiTinh(i.getGioiTinh())
                .id(i.getId())
                .taiKhoan(i.getTaiKhoan().getTaiKhoan())
                .matKhau(i.getTaiKhoan().getMatKhau())
                .build()).collect(Collectors.toList());
    }

    @Override
    public KhachHang add(KhachHang kh) {
        return null;
    }
}
