package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.ChiTietKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChiTietKhuyenMai extends JpaRepository<ChiTietKhuyenMai, Long> {
    @Query("FROM ChiTietKhuyenMai c where c.khuyenMai.id =: idkm")
    List<ChiTietKhuyenMai> showKhuyenMaiDetailS(Long idkm);
}
