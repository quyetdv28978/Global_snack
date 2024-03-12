package com.example.demo.core.Admin.repository;

import com.example.demo.entity.SanPhamChiTiet;
import com.example.demo.reponsitory.ChiTietSanPhamReponsitory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdSanPhamChiTietRepository extends ChiTietSanPhamReponsitory {
    @Query("""
       select lo.sanPhamChiTiet from LoSanPham lo where lo.trangThai = 1 and lo.soLuong >:soLuongTon
""")
    List<SanPhamChiTiet> findAllSanPhamByLoSanpham(Integer soLuongTon);
}
