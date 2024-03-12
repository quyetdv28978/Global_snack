package com.example.demo.core.khachHang.service.KHDetailService;

import com.example.demo.core.Admin.model.response.AdminSanPhamChiTietResponse;

import com.example.demo.core.khachHang.model.response.*;

import com.example.demo.entity.SanPham;
import com.example.demo.entity.SanPhamChiTiet;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetaiService {

    AdminSanPhamChiTietResponse get(Integer id);

    List<SanPhamChiTiet> getAlls();

    List<KHSanPhamChiTiet2Response> getAllByIdSp(Integer idSP) ;

    KHSanPhamResponse findById(Integer id);

    List<KhImageResponse> findImage(@Param("id") Integer id);

    List<KhTrongLuongRespon> findTrongLuong(@Param("id") Integer id);
    KHSanPhamChiTiet2Response getSanPhamChiTietByTrongLuong(String idtl, Integer idSP);

}
