//package com.example.demo.reponsitory;
//
//import com.example.demo.entity.LoSanPham;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface LoSanPhamChiTietRes extends JpaRepository<LoSanPhamChiTiet, Long> {
//    @Query("select SUM(l.soLuong) from LoSanPhamChiTiet l where l.sanPhamChiTiet.id = :idspct \n" +
//            " and l.trangThai = 1 and l.loSanPham.trangThai =1 ")
//    int sumSoLuongSanPham(@Param("idspct") int idspct);
//
//    @Query("select l.loSanPham FROM LoSanPhamChiTiet  l where l.sanPhamChiTiet.id =:idctsp and l.trangThai = 1 and l.loSanPham.trangThai = 1")
//    LoSanPham showLoSanPhamByIdCtsp(int idctsp);
//}
