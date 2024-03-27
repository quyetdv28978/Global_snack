package com.example.demo.core.Admin.model.response;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface AdminSanPhamResponse {
    @Value("#{target.id}")
    Integer getId();
    @Value("#{target.ma}")
    String getMa();

    @Value("#{target.ten}")
    String getTen();

    @Value("#{target.anh}")
    String getAnh();

    @Value("#{target.moTa}")
    String getMoTa();

    @Value("#{target.trangThai}")
    Integer getTrangThai();

    @Value("#{target.SoLuongTon}")
    Integer getSoLuongTon();

    @Value("#{target.ngayTao}")
    String getNgayTao();

    @Value("#{target.ngaySua}")
    String getNgaySua();

    @Value("#{target.thuongHieu}")
    String getThuongHieu();

    @Value("#{target.vatLieu}")
    String getVatLieu();

    @Value("#{target.loai}")
    String getLoai();


}
