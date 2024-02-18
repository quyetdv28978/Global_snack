package com.dutn.be_do_an_vat.service.base_service;

import com.dutn.be_do_an_vat.dto.DTOSanPham;
import com.dutn.be_do_an_vat.entity.SanPham;

import java.util.List;

public interface ISanPhamSer {
    List getSanPhamBy(int soLuong, int trang);
    List getSanPhamDiscount();

    SanPham themSanPham(DTOSanPham sanPham);

    SanPham updateSanPham(Long idsp, DTOSanPham sanpham);

    void deleteSanPham(Long idsp);

    SanPham searchSanPhamBy(String name, Long idsp);

}
