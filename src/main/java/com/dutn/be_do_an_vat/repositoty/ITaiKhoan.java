package com.dutn.be_do_an_vat.repositoty;

import com.dutn.be_do_an_vat.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITaiKhoan extends JpaRepository<TaiKhoan, Long> {
    Optional<TaiKhoan> findByTaiKhoan(String userName);

    @Query("FROM TaiKhoan t where t.khachHang.id =: idkh or t.nhanVien.id =: idnv")
    TaiKhoan showTaiKhoanByUserName (Long idkh, Long idnv);
    @Query("SELECT n.taiKhoan FROM NhanVien n where n.id =:idnv")
    TaiKhoan findUserByNameNhanVien(Long idnv);
}
