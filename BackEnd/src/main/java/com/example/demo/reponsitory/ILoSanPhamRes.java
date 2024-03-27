package com.example.demo.reponsitory;

import com.example.demo.core.Admin.model.response.AdminLoSanPham;
import com.example.demo.core.Admin.model.response.AdminLoSanPhamHanSuDung;
import com.example.demo.core.Admin.model.response.AdminLoSanPhamNotSoLuong;
import com.example.demo.core.Admin.model.response.AdminLoSanPhamOutDate;
import com.example.demo.entity.LoSanPham;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILoSanPhamRes extends JpaRepository<LoSanPham, Long> {
    List<LoSanPham> findAllByTrangThai(int trangThai);

    @Query(value = """
            select l.id as id, l.ten_lo as tenLo, l.ma_lo as maLo,l.ngay_het_han as ngayHetHan,
            l.trang_thai as trangThai
            from san_pham_chi_tiet spct
            RIGHT JOIN lo_san_pham l on l.id_ct_san_pham = spct.id
            where spct.id =:sanPhamChiTiet or l.id_ct_san_pham is null
            """, nativeQuery = true)
    List<AdminLoSanPhamNotSoLuong> findAllBySanPhamChiTiet(Integer sanPhamChiTiet);

    @Query(value = "SELECT l.ma_lo as maLo, l.ten_lo as tenLo, DATEDIFF(l.ngay_het_han, CURRENT_DATE()) as outdate\n" +
            "            FROM lo_san_pham l\n" +
            "            where l.trang_thai in (1,2) and DATEDIFF(l.ngay_het_han, CURRENT_DATE()) <= 7\n" +
            "            order by outdate asc", nativeQuery = true)
    List<AdminLoSanPhamOutDate> timeOutDate();

    List<AdminLoSanPham> findAllBySanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet);

    Optional<LoSanPham> findByTenLo(String tenLo);

    @Query("FROM LoSanPham  l where l.sanPhamChiTiet.id =:idctsp and l.trangThai = 1")
    LoSanPham showLoSanPhamByIdCtsp(int idctsp);

    @Query("select SUM(l.soLuong) from LoSanPham l where l.sanPhamChiTiet.id = :idspct \n" +
            " and l.trangThai in (1,2)")
    Integer sumSoLuongSanPham(@Param("idspct") int idspct);

    @Query(value = """
            select sp.ten as tenSanPham, tl.value as trongLuong, lo_san_pham.ma_lo as maLo
            , lo_san_pham.ten_lo as tenLo, lo_san_pham.ngay_het_han as ngayHetHan, lo_san_pham.so_luong as soLuong 
                       from lo_san_pham join san_pham_chi_tiet spct on spct.id = lo_san_pham.id_ct_san_pham
            join san_pham sp on sp.id = spct.id_san_pham join trong_luong tl on tl.id = spct.id_trong_luong
            where lo_san_pham.trang_thai != 4 and DATEDIFF(lo_san_pham.ngay_het_han,CURRENT_DATE()) <=:mount
            """, nativeQuery = true)
    List<AdminLoSanPhamHanSuDung> showHanSuDungSanPhamByMouth(int mount);
}
