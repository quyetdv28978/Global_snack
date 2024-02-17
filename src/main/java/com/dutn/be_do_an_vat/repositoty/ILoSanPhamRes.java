package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.LoSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoSanPhamRes extends JpaRepository<LoSanPham, Long> {
    @Query("select l.loSanPham from ChiTietLoSanPham l where l.sanPham.id=:idsp and l.sanPham.trangThai =:trangThai")
    List<LoSanPham> showAllLoSanPham(Long idsp, int trangThai);

    /**
     * Lấy lô sản phẩm có hạn sử dụng xa nhất
     *
     * @return số ngày còn hạn của lô, tên sản phẩm, tên lô và mã lô
     */
    @Query(value = "select DATEDIFF(ngay_het_han, CURRENT_DATE()), sp.ten_san_pham, l.ten_lo, l.ma_lo\n" +
            "from Lo_san_pham l\n" +
            "join chi_tiet_lo_san_pham ctlsp on l.id = ctlsp.id_lo_sp\n" +
            "join san_pham sp on ctlsp.id_sp = sp.id\n" +
            "group by sp.ten_san_pham, l.ten_lo, l.ma_lo, ngay_san_xuat, ngay_het_han\n" +
            "having DATEDIFF(ngay_het_han, ngay_san_xuat) =\n" +
            "(select DATEDIFF(ngay_het_han, ngay_san_xuat)\n" +
            "from lo_san_pham\n" +
            "order by ngay_het_han desc\n" +
            "limit 1)"
            , nativeQuery = true)
    List<Object[]> showDateMin();

    /**
     * Lấy lô sản phẩm có hạn sử dụng <= 7 ngày
     *
     * @return số ngày còn hạn của lô, tên sản phẩm, tên lô và mã lô
     */
    @Query(value = "select DATEDIFF(ngay_het_han, CURRENT_DATE()), sp.ten_san_pham, l.ten_lo, l.ma_lo\n" +
            "from Lo_san_pham l\n" +
            "join chi_tiet_lo_san_pham ctlsp on l.id = ctlsp.id_lo_sp\n" +
            "join san_pham sp on ctlsp.id_sp = sp.id\n" +
            "where l.id in (select id from lo_san_pham where DATEDIFF(ngay_het_han, CURRENT_DATE()) <= 7" +
            " and DATEDIFF(ngay_het_han, CURRENT_DATE()) > 0)\n"+
            "group by sp.ten_san_pham, l.ten_lo, l.ma_lo, ngay_san_xuat, ngay_het_han\n" +
            "order by DATEDIFF(ngay_het_han, CURRENT_DATE()) asc", nativeQuery = true)
    List<Object[]> showOutDate7Day();
}
