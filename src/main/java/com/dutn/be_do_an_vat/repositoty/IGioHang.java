package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGioHang extends JpaRepository<GioHang, Long> {
}
