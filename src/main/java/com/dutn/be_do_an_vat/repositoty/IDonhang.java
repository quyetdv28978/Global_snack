package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDonhang extends JpaRepository<HoaDon, Long> {
    List<HoaDon> findAllByTrangThai(int trangThai);
}
