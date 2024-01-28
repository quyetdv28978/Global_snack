package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDanhMuc extends JpaRepository<DanhMuc, Long> {
}
