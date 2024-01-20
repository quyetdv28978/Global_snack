package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.dto.DTOTaiKhoan;
import com.dutn.be_do_an_vat.entity.TaiKhoan;

public interface ITaiKhoanSer {
    DTOTaiKhoan themTaiKhoan(TaiKhoan taiKhoan);

    DTOTaiKhoan suaTaiKhoan(TaiKhoan taiKhoan);

    void xoaTaiKhoan(Long id);

    DTOTaiKhoan searchTaiKhoan(String name);
}
