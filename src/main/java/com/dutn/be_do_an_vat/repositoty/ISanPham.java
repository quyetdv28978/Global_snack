package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISanPham extends JpaRepository<SanPham, Long> {
    /**
     * @return danh sách sản phẩm chưa có khuyến mãi
     */
    @Query(value = "select * from san_pham as s LEFT JOIN Chi_tiet_khuyen_mai c on s.id = c.id_sp\n" +
            "LEFT join  khuyen_mai k on k.id = c.id_km\n" +
            "where s.id not in (select c.id_sp from khuyen_mai k join Chi_tiet_khuyen_mai c \n" +
            "on k.id = c.id_km\n" +
            "where k.trang_thai = 0)", nativeQuery = true)
    List showSanPhamNotDiscount();
}
