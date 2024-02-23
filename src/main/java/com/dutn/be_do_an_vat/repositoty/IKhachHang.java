package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.HoaDon;
import com.dutn.be_do_an_vat.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IKhachHang extends JpaRepository<KhachHang, Long> {
}
