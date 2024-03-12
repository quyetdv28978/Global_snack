package com.example.demo.reponsitory;

import com.example.demo.core.Admin.model.response.AdminLoSanPham;
import com.example.demo.entity.LoSanPham;
import com.example.demo.entity.SanPham;
import com.example.demo.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoSanPhamRes extends JpaRepository<LoSanPham, Long> {
//    /**
//     * Lấy lô sản phẩm có hạn sử dụng xa nhất
//     *
//     * @return số ngày còn hạn của lô, tên sản phẩm, tên lô và mã lô
//     */
//    @Query(value = "select DATEDIFF(ngay_het_han, CURRENT_DATE()), sp.ten_san_pham, l.ten_lo, l.ma_lo\n" +
//            "from Lo_san_pham l\n" +
//            "join chi_tiet_lo_san_pham ctlsp on l.id = ctlsp.id_lo_sp\n" +
//            "join san_pham sp on ctlsp.id_sp = sp.id\n" +
//            "group by sp.ten_san_pham, l.ten_lo, l.ma_lo, ngay_san_xuat, ngay_het_han\n" +
//            "having DATEDIFF(ngay_het_han, ngay_san_xuat) =\n" +
//            "(select DATEDIFF(ngay_het_han, ngay_san_xuat)\n" +
//            "from lo_san_pham\n" +
//            "order by ngay_het_han desc\n" +
//            "limit 1)"
//            , nativeQuery = true)
//    List<Object[]> showDateMin();
//
//    /**
//     * Lấy lô sản phẩm có hạn sử dụng <= 7 ngày
//     *
//     * @return số ngày còn hạn của lô, tên sản phẩm, tên lô và mã lô
//     */
//    @Query(value = "select DATEDIFF(ngay_het_han, CURRENT_DATE()), sp.ten_san_pham, l.ten_lo, l.ma_lo\n" +
//            "from Lo_san_pham l\n" +
//            "join chi_tiet_lo_san_pham ctlsp on l.id = ctlsp.id_lo_sp\n" +
//            "join san_pham sp on ctlsp.id_sp = sp.id\n" +
//            "where l.id in (select id from lo_san_pham where DATEDIFF(ngay_het_han, CURRENT_DATE()) <= 7" +
//            " and DATEDIFF(ngay_het_han, CURRENT_DATE()) > 0)\n"+
//            "group by sp.ten_san_pham, l.ten_lo, l.ma_lo, ngay_san_xuat, ngay_het_han\n" +
//            "order by DATEDIFF(ngay_het_han, CURRENT_DATE()) asc", nativeQuery = true)
//    List<Object[]> showOutDate7Day();

    List<LoSanPham> findAllByTrangThai(int trangThai);

    @Query(value = """
            select l.id as id, l.ten_lo as tenLo, l.ma_lo as maLo,l.ngay_het_han as ngayHetHan,
            l.trang_thai as trangThai
            from san_pham_chi_tiet spct
            RIGHT JOIN lo_san_pham l on l.id_ct_san_pham = spct.id
            where spct.id =:sanPhamChiTiet or l.id_ct_san_pham is null
            """, nativeQuery = true)
    List<AdminLoSanPham> findAllBySanPhamChiTiet(Integer sanPhamChiTiet);

    List<AdminLoSanPham> findAllBySanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet);
    LoSanPham findByTenLo(String tenLo);
    @Query("FROM LoSanPham  l where l.sanPhamChiTiet.id =:idctsp and l.trangThai = 1")
    LoSanPham showLoSanPhamByIdCtsp(int idctsp);

        @Query("select SUM(l.soLuong) from LoSanPham l where l.sanPhamChiTiet.id = :idspct \n" +
            " and l.trangThai in (1,2)")
    Integer sumSoLuongSanPham(@Param("idspct") int idspct);
}