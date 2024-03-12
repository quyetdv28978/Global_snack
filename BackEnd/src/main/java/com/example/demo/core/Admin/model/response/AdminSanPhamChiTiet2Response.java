package com.example.demo.core.Admin.model.response;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AdminSanPhamChiTiet2Response {
    @Value("#{target.id}")
    Integer getId();

    @Value("#{target.ma}")
    String getMa();

    @Value("#{target.ten}")
    String getTen();

    @Value("#{target.giaBan}")
    BigDecimal getGiaBan();

    @Value("#{target.giaNhap}")
    BigDecimal getGiaNhap();

    @Value("#{target.soLuongTon}")
    Integer getSoLuongTon();

    @Value("#{target.trangThai}")
    Integer getTrangThai();

    @Value("#{target.moTa}")
    String getMoTa();

    @Value("#{target.loai}")
    String getLoai();

    @Value("#{target.thuongHieu}")
    String getThuongHieu();

    @Value("#{target.trongLuong}")
    String getTrongLuong();

    @Value("#{target.anh}")
    String getAnh();

    @Value("#{target.tenKM}")
    String getTenKM();

    @Value("#{target.thoiGianBatDau}")
    String getThoiGianBatDau();

    @Value("#{target.thoiGianKetThuc}")
    String getThoiGianKetThuc();

    @Value("#{target.giaSauGiam}")
    Integer getGiaSauGiam();

    @Value("#{target.giaTriGiam}")
    Integer getGiaTriGiam();

    @Value("#{target.maLo}")
    String getMaLo();
    @Value("#{target.tenLo}")
    String getTenLo();
    @Value("#{target.ngayHetHan}")
    LocalDate getNgayHetHan();
}
