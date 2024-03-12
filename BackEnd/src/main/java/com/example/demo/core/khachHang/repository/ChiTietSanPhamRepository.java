package com.example.demo.core.khachHang.repository;

import com.example.demo.entity.SanPhamChiTiet;
import com.example.demo.reponsitory.ChiTietSanPhamReponsitory;
import org.springframework.stereotype.Repository;

@Repository

public interface ChiTietSanPhamRepository extends ChiTietSanPhamReponsitory {

    SanPhamChiTiet findSanPhamChiTietsById(Integer id);


}
