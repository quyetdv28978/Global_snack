package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaiKhoan extends JpaRepository<TaiKhoan, Long> {
}
