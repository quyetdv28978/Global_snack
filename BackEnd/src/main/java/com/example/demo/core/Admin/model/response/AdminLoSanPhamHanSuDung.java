package com.example.demo.core.Admin.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface AdminLoSanPhamHanSuDung {
    @Value("#{target.tenSanPham}")
    String getTenSanPham();

    @Value("#{target.trongLuong}")
    Integer getTrongLuong();

    @Value("#{target.maLo}")
    String getMaLo();

    @Value("#{target.tenLo}")
    String getTenLo();

    @Value("#{target.ngayHetHan}")
    String getNgayHetHan();

    @Value("#{target.soLuong}")
    Integer getSoLuong();
}
