package com.example.demo.core.Admin.model.response;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface AdminLoSanPham {
    @Value("#{target.id}")
    Integer getId();

    @Value("#{target.maLo}")
    String getMaLo();
    @Value("#{target.tenLo}")
    String getTenLo();

    @Value("#{target.ngayHetHan}")
    LocalDate getNgayHetHan();
    @Value("#{target.trangThai}")
    Integer getTrangThai();
}
