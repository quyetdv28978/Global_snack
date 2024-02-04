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
}
