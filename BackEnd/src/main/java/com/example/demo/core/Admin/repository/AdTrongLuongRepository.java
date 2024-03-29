package com.example.demo.core.Admin.repository;

import com.example.demo.entity.SanPham;
import com.example.demo.entity.TrongLuong;
import com.example.demo.entity.VatLieu;
import com.example.demo.reponsitory.TrongLuongRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdTrongLuongRepository extends TrongLuongRepository {
    @Query("select  pot from  TrongLuong  pot where pot.donVi like :keyword or pot.ma like :keyword")
    Page<TrongLuong> search(String keyword, Pageable pageable);
    List<TrongLuong> findAllByTrangThai(Integer trangThai, Sort sort);

    @Query("from  TrongLuong  pot where pot.value like :keyword ")
    Optional<TrongLuong> findByTenTrongLuongExcel(Integer keyword);
    @Query("from  TrongLuong  pot where pot.value =:keyword and pot.donVi =:donvi  ")
    Optional<TrongLuong> findByTenTrongLuongAndDonViExcel(Integer keyword, String donvi);
}
