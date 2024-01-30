package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKieuThanhToan extends JpaRepository<ThanhToan, Long> {
}
