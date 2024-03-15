package com.example.demo.reponsitory;

import com.example.demo.entity.SanPhamOutDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamOutDateRepository extends JpaRepository<SanPhamOutDate, Long> {
}
